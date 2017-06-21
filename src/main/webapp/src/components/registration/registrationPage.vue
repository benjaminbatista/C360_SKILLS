<template>

  <form id="registr-form" @submit.prevent="verifyForm">
    <customInput
      label="login"
      labelText="Code de login"
      icon="glyphicon-th"
      type="text"
      tab="1"
      placeholder="ABC1234"
      v-model="personnalIdNumber"
      @focus="setLoginEmptyToFalse()"
      @blur="isLoginEmpty"
      maxlength="20" minlength="2"
      :emptyField="loginEmpty"
      :existField="personalIdNumberAlreadyExist"
      existMessage="Ce code de login a déjà été enregistré"
      :errorField="isErrorLogin()"
      :errorMessage="errorMessageLogin">
    </customInput>
    <!-- NOM -->
    <customInput
      label="nom"
      labelText="Nom"
      icon="glyphicon-user"
      type="text"
      tab="2"
      placeholder="DUPONT"
      v-model="lastName"
      @focus="setLastNameEmptyToFalse()"
      @blur="isLastNameEmpty"
      maxlength="125" minlength="2"
      :emptyField="lastNameEmpty"
      existField=""
      :errorField="isErrorLastName()"
      :errorMessage="errorMessageLastName">
    </customInput>
    <!-- PRENOM -->
    <customInput
      label="prenom"
      labelText="Prénom"
      icon="glyphicon-user"
      type="text"
      tab="2"
      placeholder="Eric"
      v-model="firstName"
      @focus="setFirstNameEmptyToFalse()"
      @blur="isFirstNameEmpty"
      maxlength="125" minlength="2"
      :emptyField="firstNameEmpty"
      existField=""
      :errorField="isErrorFirstName()"
      :errorMessage="errorMessageFirstName">
    </customInput>
    <!-- EMAIL-->
    <customInput
      label="email"
      labelText="Email"
      icon="glyphicon-envelope"
      type="text"
      tab="2"
      placeholder="eric.dupont@viseo.com"
      v-model="email"
      @focus="setEmailAlreadyExistToFalse()"
      @blur="isEmailEmpty"
      :emptyField="emailEmpty"
      :existField="emailAlreadyExist"
      existMessage="Cet email a déjà été enregistré."
      :errorField="isErrorEmail()"
      :errorMessage="errorMessageEmail">
    </customInput>
    <!-- MOT DE PASSE -->
    <customPasswordInput
      label="mdp"
      labelText="Mot de passe"
      v-model="password"
      @focus="setPasswordEmptyToFalse()"
      @blur="isPasswordEmpty"
      :emptyField="passwordEmpty"
      :errorField="isErrorPassword()"
      :errorMessage="errorMessagePassword"
      :show="showPass"
      @click="toggleShowPassword">
    </customPasswordInput>
    <!-- CONFIRMATION MOT DE PASSE -->
    <customPasswordInput
      label="mdpc"
      labelText="Confirmation mot de passe"
      v-model="confirmPassword"
      @focus="setConfirmPasswordEmptyToFalse()"
      @blur="isConfirmPasswordEmpty"
      :emptyField="confirmPasswordEmpty"
      :errorField="isErrorConfirmPassword()"
      :errorMessage="errorMessageConfirmPassword"
      :show="showPassConf"
      @click="toggleShowPasswordConfirmation">
    </customPasswordInput>
    <div class="form-group">
      <div class="row">
        <div class="col-xs-12 col-xm-12 col-md-12 cold-lg-12 ">
          <button type="submit" name="register-submit" id="register-submit"
                  tabindex="4" class="form-control btn btn-primary">S'inscrire
          </button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  import customInput from '../customInput/customInput.vue'
  import passwordInput from '../customInput/passwordInput.vue'
  import config from '../../config/config'
  import axios from 'axios'

  export default {

      name: 'inscription-form',
    components: { customInput: customInput, customPasswordInput: passwordInput },
    data() {
        return {
          collaborator: {
            personnalIdNumber: '',
            lastName: '',
            firstName: '',
            email: '',
            password: '',
            confirmPassword: '',
          },
          user: {
            email: '',
            password: ""
          },
          userToConnect: '',
          personnalIdNumber: '',
          lastName: '',
          firstName: '',
          email: '',
          password: '',
          confirmPassword: '',
          errorMessageLogin: '',
          errorMessageLastName: '',
          errorMessageFirstName: '',
          errorMessageEmail: '',
          errorMessagePassword: '',
          errorMessageConfirmPassword: '',
          collaboratorToRegister: {},
          verif: true,
          personalIdNumberAlreadyExist: false,
          emailAlreadyExist: false,
          loginEmpty: false,
          lastNameEmpty: false,
          firstNameEmpty: false,
          emailEmpty: false,
          passwordEmpty: false,
          confirmPasswordEmpty: false,
          showPass: false,
          showPassConf: false,
          border: 'color-red',
          isLoginValid: true,
          isLastNameValid: true,
          isFirstNameValid: true,
          isEmailValid: true,
          isPasswordValid: true,
          isConfirmPasswordValid: true
        }
      },
    mounted: function () {
    },
    watch: {
      personnalIdNumber: function (value) {
        this.verifyLogin(value);
      },
      lastName: function (value) {
        this.verifyLastName(value);
      },
      firstName: function (value) {
        this.verifyFirstName(value);
      },
      email: function (value) {
        this.verifyEmail(value);
      },
      password: function (value) {
        this.verifyPassword(value);
        if (this.confirmPassword != '')
          this.verifyConfirmPassword(value);
      },
      confirmPassword: function (value) {
        this.verifyConfirmPassword(value);
      }
    },
    methods: {
      isErrorLogin() {
        return !this.isLoginValid && !this.loginEmpty;
      },
      isErrorLastName() {
        return !this.isLastNameValid && !this.lastNameEmpty;
      },
      isErrorFirstName() {
        return !this.isFirstNameValid && !this.firstNameEmpty;
      },
      isErrorEmail() {
        return !this.isEmailValid && !this.emailEmpty;
      },
      isErrorPassword() {
        return !this.isPasswordValid && !this.passwordEmpty;
      },
      isErrorConfirmPassword() {
        return !this.isConfirmPasswordValid && !this.confirmPasswordEmpty;
      },
      toggleShowPassword() {
        this.showPass = !this.showPass;
      },
      toggleShowPasswordConfirmation() {
        this.showPassConf = !this.showPassConf;
      },
      setLoginEmptyToFalse() {
        this.loginEmpty = false;
      },
      setLastNameEmptyToFalse() {
        this.lastNameEmpty = false;
      },
      setFirstNameEmptyToFalse() {
        this.firstNameEmpty = false;
      },
      setEmailAlreadyExistToFalse() {
        this.emailAlreadyExist = false;
        this.emailEmpty = false
      },
      setPasswordEmptyToFalse() {
        this.passwordEmpty = false
      },
      setConfirmPasswordEmptyToFalse() {
        this.confirmPasswordEmpty = false
      },
      verifyLogin(personnalIdNumber) {
        this.personalIdNumberAlreadyExist = false;
        this.loginEmpty = false;
        if (/^[A-Z]{3}[0-9]{4}$/.test(personnalIdNumber)) {
          this.errorMessageLogin = '';
          this.isLoginValid = true;
        } else {
          this.errorMessageLogin = 'Veuillez entrer un code de login valide';
          this.isLoginValid = false;
        }
      },

      isLoginEmpty(){
        if (this.personnalIdNumber == '') {
          this.loginEmpty = true;
        }
      },

      verifyLastName(lastName) {
        if (/^(([a-zA-ZÀÁÂÃÄÅàáâãäåÒÓÔÕÖØòóôõöøÈÉÊËèéêëÇçÌÍÎÏìíîïÙÚÛÜùúûüÿÑñ.'-]+[\s]{0,1})+[a-zA-ZÀÁÂÃÄÅàáâãäåÒÓÔÕÖØòóôõöøÈÉÊËèéêëÇçÌÍÎÏìíîïÙÚÛÜùúûüÿÑñ.'-]*){2,125}$/.test(lastName)) {
          this.errorMessageLastName = '';
          this.isLastNameValid = true;
        } else {
          this.errorMessageLastName = 'Veuillez entrer un nom valide';
          this.isLastNameValid = false;
        }
      },

      isLastNameEmpty(){
        if (this.lastName == '') {
          this.lastNameEmpty = true;
        }
      },

      verifyFirstName(firstName) {
        if (/^(([a-zA-ZÀÁÂÃÄÅàáâãäåÒÓÔÕÖØòóôõöøÈÉÊËèéêëÇçÌÍÎÏìíîïÙÚÛÜùúûüÿÑñ.'-]+[\s]{0,1})+[a-zA-ZÀÁÂÃÄÅàáâãäåÒÓÔÕÖØòóôõöøÈÉÊËèéêëÇçÌÍÎÏìíîïÙÚÛÜùúûüÿÑñ.'-]*){2,125}$/.test(firstName)) {
          this.errorMessageFirstName = '';
          this.isFirstNameValid = true;
        } else {
          this.errorMessageFirstName = 'Veuillez entrer un prénom valide';
          this.isFirstNameValid = false;
        }
      },

      isFirstNameEmpty(){
        if (this.firstName == '') {
          this.firstNameEmpty = true;
        }
      },

      verifyEmail(email){
        if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((([0-9]{1,3}\.)+[0-9]{1,3})|(([a-zA-ZàÀÁÂÃÄÅàáâãäåÒÓÔÕÖØòóôõöøÈÉÊËèéêëÇçÌÍÎÏìíîïÙÚÛÜùúûüÿÑñ\-0-9]+\.)+[a-zA-Z0-9]{2,}))$/
            .test(email)) {

          this.errorMessageEmail = '';
          this.isEmailValid = true;
        } else {
          this.errorMessageEmail = 'Veuillez entrer un email valide';
          this.isEmailValid = false;
        }
      },

      isEmailEmpty(){
        if (this.email == '') {
          this.emailEmpty = true;
        }
      },

      verifyPassword(password) {
        if (/^(.){6,125}$/.test(password)) {
          this.errorMessagePassword = '';
          this.isPasswordValid = true;
        } else {
          this.errorMessagePassword = 'Le mot de passe doit avoir au minimum 6 caractères';
          this.isPasswordValid = false;
        }
      },

      isPasswordEmpty(){
        if (this.password == '') {
          this.passwordEmpty = true;
        }
      },

      verifyConfirmPassword(confirmPassword) {
        if (this.confirmPassword === this.password) {
          this.errorMessageConfirmPassword = '';
          this.isConfirmPasswordValid = true;
        } else {
          this.errorMessageConfirmPassword = 'La confirmation du mot de passe n\'est pas valide';
          this.isConfirmPasswordValid = false;
        }
      },

      isConfirmPasswordEmpty(){
        if (this.confirmPassword == '') {
          this.confirmPasswordEmpty = true;
        }
      },
      handleCookie(token) {
        document.cookie = "token=" + token;
        document.cookie = "stayconnected=true";
      },

      addCollaborator(){
        delete this.collaboratorToRegister['confirmPassword'];
                let sendUserToRegisterSuccess = (response) => {
          this.emailAlreadyExist = false;
          this.personalIdNumberAlreadyExist = false;
          this.user.email = this.collaborator.email;
          this.user.password = this.collaborator.password;
          this.userToConnect = JSON.parse(JSON.stringify(this.user));
          this.verifyUserToConnectByDatabase();
        };

        let sendUserToRegisterError = (response) => {
          console.log("Error: ", response);
          if (response.data.message == "personnalIdNumber") {
            console.log("PID already exist");
            this.personalIdNumberAlreadyExist = true;
            this.emailAlreadyExist = false;
          }
          else if (response.data.message == "email") {
            console.log("email already exist");
            this.emailAlreadyExist = true;
            this.personalIdNumberAlreadyExist = false;
          } else {
            console.error(response);
            this.personalIdNumberAlreadyExist = true;
            this.emailAlreadyExist = true;
          }
        };
        axios.post('http://localhost:8086/api/collaborateurs',
              this.collaboratorToRegister,sendUserToRegisterSuccess, sendUserToRegisterError )
         .then( function (response){
             console.log('saved successfly');
        });
      },

      verifyUserToConnectByDatabase(){
        let redirectDependingOnRole = (token) => {
          if (typeof token.data['userConnected'] != 'undefined') {
            if (jwt_decode(token.data['userConnected']).roles) {
              this.goTo('addTrainingTopic');
            }
            else {
              this.goTo('registerTrainingCollaborator');
            }
          }
        };

        let connectUser = (userPersistedToken) => {
          this.handleCookie(userPersistedToken.data['userConnected']);
          redirectDependingOnRole(userPersistedToken);
        };

        axios.post("http://localhost:8086/api/user", this.userToConnect, connectUser)
          .then( function (response){
            console.log('success');
          });
      },

        this.post(config.server+"/api/collaborateurs/", this.collaboratorToRegister, sendUserToRegisterSuccess, sendUserToRegisterError)
      },

      verifyUserToConnectByDatabase(){
        let redirectDependingOnRole = (token) => {
          if (typeof token.data['userConnected'] != 'undefined') {
            if (jwt_decode(token.data['userConnected']).roles) {
              this.goTo('addTrainingTopic');
            }
            else {
              this.goTo('registerTrainingCollaborator');
            }
          }
        };

        let connectUser = (userPersistedToken) => {
          this.handleCookie(userPersistedToken.data['userConnected']);
          redirectDependingOnRole(userPersistedToken);
        };

        this.post(config.server+"api/user", this.userToConnect, connectUser);
      },

      verifyForm (){
        this.lastName = this.lastName.replace(/ +/g, " ").replace(/ +$/, "");
        this.firstName = this.firstName.replace(/ +/g, " ").replace(/ +$/, "");
        this.isLoginEmpty();
        this.isLastNameEmpty();
        this.isFirstNameEmpty();
        this.isEmailEmpty();
        this.isPasswordEmpty();
        this.isConfirmPasswordEmpty();
        if (!this.loginEmpty && !this.lastNameEmpty && !this.firstNameEmpty && !this.emailEmpty && !this.passwordEmpty && !this.confirmPasswordEmpty && this.isPasswordValid && this.isConfirmPasswordValid) {
          this.personalIdNumberAlreadyExist = false;
          this.emailAlreadyExist = false;
          this.collaborator.personnalIdNumber = this.personnalIdNumber;
          this.collaborator.lastName = this.lastName;
          this.collaborator.firstName = this.firstName;
          this.collaborator.email = this.email;
          this.collaborator.password = this.password;
          this.collaborator.confirmPassword = this.confirmPassword;
          this.collaboratorToRegister = JSON.parse(JSON.stringify(this.collaborator));
          this.addCollaborator();
        }
      },
      }
    }
  }
</script>
