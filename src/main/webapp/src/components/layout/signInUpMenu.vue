<template>
  <div class="col-lg-8 col-sm-12 col-xs-12 col-md-6 col-lg-6 col-lg-offset-3  col-md-offset-3">
    <div style="box-shadow: 0 2px 5px 1px rgba(0, 0, 0, 0.2);
                 border-radius: 3px 3px 0 0;">
      <ul class="tab-group">
        <li :class="tabinscription">
          <a ref='inscriptionButton' @click="showInscriptionForm()">Inscription</a>
        </li>
        <li :class="tabconnexion">
          <a ref="connexionButton" @click="showConnexionForm()">Connexion</a>
        </li>
      </ul>
      <div class="panel-body">
        <div class="row">
          <div class="col-xs-12 col-xm-12 col-md-6 cold-lg-6 col-offset-3 col-md-offset-3">
            <inscriptionform ref="insc" v-if="newCollab"></inscriptionform>
            <connexionform ref="conn" v-else></connexionform>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import registrationPage from '../registration/registrationPage.vue'
  import logInPage from '../logIn/logInPage.vue'

  export default {
    name: 'navigation-menu',
    components: { inscriptionform: registrationPage, connexionform: logInPage },
    data() {
      return {
        color_inscription: 'color-red',
        color_connexion: 'color-red',
        tabconnexion: "tab active",
        tabinscription: "tab",
        newCollab: false
      }
    },
  beforeCreate: function () {

    let isTokenPresent = document.cookie.match('(^|;)\\s*' + "token" + '\\s*=\\s*([^;]+)');

    let redirectToTrainingCollaborator = (response) => {
      if (response) {
        this.goTo('registerTrainingCollaborator')
      }
    };

    if (isTokenPresent) {
      let token = String(isTokenPresent.pop());
      this.post(config.server+"api/sendtoken", token, redirectToTrainingCollaborator);
    }
  },

  mounted: function () {
  },

  methods: {
    showInscriptionForm() {
      this.tabinscription = 'tab active';
      this.tabconnexion = 'tab';
      this.newCollab = true;
    },
    showConnexionForm() {
      this.tabinscription = 'tab';
      this.tabconnexion = 'tab active';
      this.newCollab = false;
    }
  }
}
</script>

<style>
  .panel .panel-default{
    margin-top: 20%;
  }

  .color-blue {color: #1E90FF;}
  .color-grey {color: #A9A9A9;}
  .color-red {color: rgb(224,53,89)}

  .fontsize {
    font-size: 150%;
  }
  /* enable absolute positioning */
  .inner-addon {
    position: relative;
  }

  /* style icon */
  .inner-addon .glyphicon {
    position: absolute;
    padding: 10px;
    pointer-events: none;
  }

  /* align icon */
  .left-addon .glyphicon-left { left:  0px;}
  .right-addon .glyphicon-right { right: 0px;}

  /* add padding  */
  .left-addon input  { padding-left:  30px; }
  .right-addon input { padding-right: 30px; }
  .password{
    position: relative;
  }

  .password .glyphicon-eye-open,#password2 .glyphicon-eye-open {
    right: 10px;
    position: absolute;
    top: 12px;
    cursor:pointer;
  }
  .password .glyphicon-eye-close,#password2 .glyphicon-eye-close {
    right: 10px;
    position: absolute;
    top: 12px;
    cursor:pointer;
  }
  .password .glyphicon-ok-circle,.glyphicon-remove-circle{
    left: 120px;
    position: absolute;
    top: 12px;
    margin-left: 50%;
  }
  .password .glyphicon-lock,#password2 .glyphicon-lock {

    left: 10px;
    position: absolute;
    top: 12px;
    cursor:pointer;
    pointer-events: none;
  }
  .password input[type="password"]{
    padding-right: 30px;
    padding-left: 30px;
  }
  .password input[type="text"]{
    padding-right: 30px;
    padding-left: 30px;
  }


  .tab-group {
    list-style: none;
    padding: 0;
    margin: 0 0 40px 0;
  }
  .tab-group:after {
    content: "";
    display: table;
    clear: both;
  }
  .tab-group li a {
    display: block;
    text-decoration: none;
    padding: 15px;
    background: rgba(160, 179, 176, 0.25);
    color: #a0b3b0;
    font-size: 20px;
    float: left;
    width: 50%;
    text-align: center;
    cursor: pointer;
    -webkit-transition: .5s ease;
    transition: .5s ease;
  }
  .tab-group li a:hover {
    background: #8db9df;
    color: #ffffff;
  }
  .tab-group .active a {
    background: #428bca;
    color: #ffffff;
  }

  .tab-content > div:last-child {
    display: none;
  }

  [v-cloak] {
    display: none;
  }

  .forgotPassword {
    float:right;
  }

  /* Popup container */
  .popup {
    position: relative;
    display:inline-block;
    cursor: pointer;

  }

  /* The actual popup (appears on top) */
  .popup .popuptext {

    background-color: #F0F0F0;
    color: #2b2b29;
    text-align: center;
    border-radius: 6px;
    padding: 8px 0;
    position: absolute;
    z-index: 1;
    padding:10px;
    bottom: 400%;
    left: 75%;
    margin-left: -80px;
  }

  .slideInUp {
    -webkit-animation-name: slideInUp;
    animation-name: slideInUp;
    -webkit-animation-duration: 1s;
    animation-duration: 1s;
  }

  /* Popup arrow */
  .popup .popuptext:after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #F0F0F0 transparent transparent transparent;
  }

  /* this border color controlls the outside, thin border */
  .popup .popuptext:before {
    top: 9px;
    border-color: #a00 transparent transparent transparent;
    border-width: 11px;
  }

  .tab.active a{
    background: rgb(224,53,89);
  }

  .tab-group li a:hover {
    background: rgb(151, 37, 61);
  }
</style>
