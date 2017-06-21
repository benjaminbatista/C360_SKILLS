<template>
  <form id="registr-form" @submit.prevent="VerifyForm">
    <table style="border-spacing: 0px">
      <!-- EMAIL-->
      <div class="form-group" :class="{'has-error':emailEmpty || !isNotNewEmail}">
        <label for="email">Email</label>
        <div class="inner-addon left-addon" :class="{ 'control': true }">
          <tr><td style="width: 500px;">
            <i class="glyphicon glyphicon-envelope"></i>
            <input ref="inputMail" type="email"  name="email" id="email" tabindex="2"  class="form-control"  placeholder="eric.dupont@viseo.com"
                   v-model="email" @focus="emailEmpty = false; isNotNewEmail = true; showPopup = false;"  @blur="isEmailEmpty" onfocus="this.placeholder = ''"
                   onblur="this.placeholder = 'eric.dupont@viseo.com'">
          </td></tr>
          <tr><td style="height: 20px;">
            <span v-show="emailEmpty" class="color-red ">Email est obligatoire.</span>
            <span v-show="!isNotNewEmail && !emailEmpty" class="color-red ">Cet email n'est associé à aucun compte</span>
          </td></tr>
        </div>
      </div>
      <!-- MOT DE PASSE -->
      <div class="form-group" :class="{'has-error':passwordEmpty }">
        <label for="mdp">Mot de passe</label>
        <div class="password" :class="{ 'control': true }">
          <tr><td style="width: 500px;">
            <i class="glyphicon glyphicon-lock"></i>
            <span @click="showPass = !showPass" v-show="!showPass && password" class="glyphicon glyphicon-eye-open"> </span>
            <span @click="showPass = false" v-show="showPass && password" class="glyphicon glyphicon-eye-close"> </span>
            <input ref="inputPassword" type="password" v-model="password" v-show="!showPass" name="mdp" id="mdp" tabindex="2" class="form-control"
                   placeholder="••••••" onfocus="this.placeholder = ''" onblur="this.placeholder = '••••••'" @focus="passwordEmpty = false; showPopup = false;"
                   @blur="isPasswordEmpty">
            <input ref="inputPasswordVisible" type="text" v-model="password" v-show="showPass"  name="mdp" id="mdp2" tabindex="2" class="form-control"
                   @focus="passwordEmpty = false" @blur="isPasswordEmpty">
          </td></tr>
          <tr><td style="height: 20px;">
            <span v-show="passwordEmpty"  class="color-red ">Mot de passe est obligatoire.</span>
          </td></tr>
        </div>
      </div>
      <div class="checkbox">
        <div class = "row">
          <div class = "col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label><input type="checkbox" value="" v-model="stayConnected">Rester Connecté</label>
          </div>
          <div class = "col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <a href="#" ref="forgotPassword" @click="showPopupFn" class="forgotPassword">Mot de passe oublié</a><br>
          </div>
        </div>
        <span v-show="isErrorAuthentification" class="color-red text-justify">Connexion refusée: veuillez entrer une adresse e-mail et un mot de passe valide</span>
        <div class="popup col-md-12 col-sm-12 col-lg-12" v-show="showPopup">
          <span class="popuptext animated slideInUp" id="myPopup">Le mot de passe a été envoyé à {{email}}</span>
        </div>
      </div>
      <div class="form-group">
        <div class="row">
          <div class="col-xs-12 col-xm-12 col-md-12 cold-lg-12 ">
            <button ref="submitConnexion" type="submit" name="register-submit" id="register-submit"
                    tabindex="4" class="form-control btn btn-primary" @click="sendInformationToCookie()">Se connecter
            </button>
          </div>
        </div>
      </div>
    </table>
  </form>
</template>

<script>
  import config from '../../config/config'
  import axios from 'axios'

  export default {
      name: 'connexion-form',
    data(){
        return {
          user: {
            email: '',
            password: '',
          },
          email: '',
          password: '',
          userToRegister: {},
          isErrorAuthentification: false,
          emailEmpty: false,
          passwordEmpty: false,
          showPass: false,
          stayConnected: true,
          showPopup: false,
          border: 'color-red',
          allUsers: [],
          isNotNewEmail: true,
          emailToSend: '',
          passwordToSend: '',
          idToSend: '',
          lastNameToSend: '',
          firstNameToSend: ''
        }
      },
    mounted: function () {
    },
    methods: {

      handleCookie(token) {
        if (this.stayConnected) {
          document.cookie = "token=" + token + ";expires=Fri, 31 Dec 9999 23:59:59 GMT";
          document.cookie = "stayconnected=" + this.stayConnected + ";expires=Fri, 31 Dec 9999 23:59:59 GMT";
        }
        else {
          document.cookie = "token=" + token;
          document.cookie = "stayconnected=" + this.stayConnected;
        }
      },

      showPopupFn() {
        if (this.email == '') {
          this.emailEmpty = true;
        } else {
          this.gatherUsersFromDatabaseToVerify();
        }
      },

      isEmailEmpty(){
        if (this.email == '') {
          this.emailEmpty = true;
        }
      },

      isPasswordEmpty(){
        if (this.password == '') {
          this.passwordEmpty = true;
        }
      },

      VerifyForm(){
        this.isEmailEmpty();
        this.isPasswordEmpty();
        if (!this.emailEmpty && !this.passwordEmpty) {
          this.user.email = this.email;
          this.user.password = this.password;
          this.userToRegister = JSON.parse(JSON.stringify(this.user));
          this.verifyUserByDatabase();
        }
      },

      verifyUserByDatabase(){
        let connectUserSuccess = (userPersistedToken) => {
          this.handleCookie(userPersistedToken.data['userConnected']);
          if (typeof userPersistedToken.data['userConnected'] != 'undefined') {
            if (jwt_decode(userPersistedToken.data['userConnected']).roles) {
              this.goTo('addTrainingTopic');
            }
            else
              this.goTo('registerTrainingCollaborator');
          }
        };

        let connectUserError = () => {
          this.password = "";
          this.user.password = "";
          this.isErrorAuthentification = true;
        };

        axios.post(config.server + "/api/user", this.userToRegister, connectUserSuccess, connectUserError);
      },

      gatherUsersFromDatabaseToVerify(){
          let self = this;
        axios.get(config.server + "/api/collaborateurs").then(
          function (response) {
           self.allUsers = response.data;
            console.log("la repéonse" + response.data);
          },
          function (response) {
            console.log("Error: ", response);
            console.error(response);
          }
        ).then(
          function () {
            self.VerifyEmailFromDatabase();
            self.isErrorAuthentification = false;
            if (self.isNotNewEmail == true) {
              let self = this;
              axios.post(config.server +"/api/sendemail/", self.idToSend);
              this.showPopup = true;
              setTimeout(function () {
                self.showPopup = false;
              }, 10000);
            }
          }
        )
      },

      sendInformationToCookie(){
        let self = this;
        axios.get(config.server + "/api/collaborateurs").then(
          function (response) {
            self.allUsers = response.data;
          },
          function (response) {
            console.log("Error: ", response);
            console.error(response);
          }
        ).then(
          function () {
            for (let tmp in self.allUsers) {
              if (self.email == self.allUsers[tmp].email) {
                self.emailToSend = self.allUsers[tmp].email;
                self.passwordToSend = self.allUsers[tmp].password;
                self.idToSend = self.allUsers[tmp].id;
                self.lastNameToSend = self.allUsers[tmp].lastName;
                self.firstNameToSend = self.allUsers[tmp].firstName;
                self.isNotNewEmail = true;
                break;
              }
            }
          }
        )
     },

      VerifyEmailFromDatabase(){
        let self = this;
        self.isNotNewEmail = false;
        for (var tmp in self.allUsers) {
          if (self.email == self.allUsers[tmp].email) {
            self.emailToSend = self.allUsers[tmp].email;
            self.passwordToSend = self.allUsers[tmp].password;
            self.idToSend = self.allUsers[tmp].id;
            self.lastNameToSend = self.allUsers[tmp].lastName;
            self.firstNameToSend = self.allUsers[tmp].firstName;
            self.isNotNewEmail = self;
            break;
          }
        }
      }
    }
  }
</script>
 <style>


 </style>
