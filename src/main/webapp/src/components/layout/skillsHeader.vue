<template>
  <div id="wrap">
    <div class="navbar navbar-default navbar-fixed-top" id="header-color">
      <div class="container-fluid" id="header-component">
        <div class="row">
          <div id="custom-navbar"
               class="col-lg-4 col-md-6 col-sm-6 col-xs-6 navbar-header">
            <p id="navbar-title"
               href="#">Collaborateur 360</p>
            <p id="navbar-subtitle">{{ title }}</p>
          </div>
          <div id="navbar-right-part"
               class="col-lg-4 col-lg-offset-4 col-md-5 col-sm-5 col-xs-5"
              >
            <div id="navbar-user"
                 class="col-lg-7 col-lg-offset-1 col-md-8 col-sm-8 col-xs-9 text-right"
                >
              <div class="col-lg-2 col-md-5 col-sm-5 col-xs-5">
                <img class="image-min"
                     src="../../assets/profile.jpg">
              </div>
              <span class="text-left col-lg-8 col-lg-offset-2 col-md-5 col-sm-5 col-xs-5" style="margin-top:10px" >{{ firstName }} {{ lastName }}</span>
              <!--<dropdown class="col-lg-8 col-lg-offset-2 col-md-5 col-sm-5 col-xs-5" type="default" v-if="showPicture()" v-show="showDisconnexion()" text="Choisissez une action" id="menu">-->
                <!--<li><a @click="goTo('registerTrainingCollaborator');">Espace formations</a></li>-->
                <!--<li><a @click="goTo('profiltoupdate');">Modifier mon profil</a></li>-->
                <!--<li role="separator" class="divider"></li>-->
                <!--<li><a @click="disconnectUser">Déconnexion</a></li>-->
              <!--</dropdown>-->


            </div>

            <div style="margin-top:10px" class="col-lg-4 col-md-4 col-sm-4 col-xs-3">
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <span id="navbar-app" class="col-lg-2 col-sm-2 col-md-2 glyphicon glyphicon-th dropdown-toggle"
                          data-toggle="dropdown" aria-hidden="true" href="#"></span>
                    <span class="caret"></span>

                  <ul id="dropdown-app" class="dropdown-menu">
                    <li>
                      <span class="col-lg-5 col-md-6 col-sm-6 col-xs-6"><img src="../../assets/icon_cv.png"
                                                                             class="text-center  icon-app"><p>GCv</p></a></span>
                      <span class="col-lg-5 col-md-6 col-sm-6 col-xs-6"><img src="../../assets/icon_conge.png"
                                                                             class="text-center icon-app"><p>GCon</p></span>
                      <span class="col-lg-5 col-md-6 col-sm-6 col-xs-6"><img src="../../assets/icon_formation.png"
                                                                             class="text-center icon-app"><p>GF</p></a></span>
                      <span class="col-lg-5 col-md-6 col-sm-6 col-xs-6"><img src="../../assets/icon_mission.png"
                                                                             class="text-center icon-app"><p>GM</p></span>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
  var $ = window.jQuery = require('jquery');

  export default{
      props:["name"],
    data () {
      return {
        lastName: 'Ben Gamra',
        firstName: 'Nihel',
        title:'Gestion des compétences',
        disconnect: false,
        dialog: false


      }
    },

    computed: function(){

    },

    mounted: function () {
      $('ul.nav li.dropdown').hover(function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
      }, function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
      });

    },
    method: {
      setDisconnectedToTrue(){
        this.disconnect = true;
      },
      setDisconnectedToFalse(){
        this.disconnect = false;
      },
      showDisconnexion(){
        return this.disconnect && !this.dialog;
      },
      showName(){
        return !this.disconnect && !this.dialog;
      },
    }
  }
</script>

<style>

  #header-component {
    height: 65px;
  }

  #header-color {
    background-color: rgb(224, 53, 89);
  }

  #wrap {
    margin-bottom: 100px;
  }

  #custom-navbar {
    padding: 10px 30px 10px 10px;
  }

  #wrap p, #wrap span {
    color: white;
  }

  #custom-navbar p {
    margin: 0px;
  }

  #navbar-title {
    font-size: 1.3em;
  }

  #navbar-right-part {
    float: right;
    margin: 10px 0px;
    padding: 0px;
  }

  #navbar-user {
    display: inline-block;
    text-align: right;
    color: #6a6a6a;
  }

  .dropdown-menu {
    min-width: 130px;
    left: -75%;
    top: 150%;
  }

  #dropdown-app {
    padding-top: 15px;
  }

  #dropdown-app p {
    text-align: center;
  }

  .icon-app {
    margin-bottom: 15px;
  }

  .icon-app:hover {
    filter: brightness(70%);
  }

  #btn-disconnect {
    padding:1% 5%;
    font-size:95%;
    box-shadow: none;
    background-color: #dcdcdc;
    border: solid 1px #dcdcdc;
    border-radius:15px;
  }

  #navbar-app {
    font-size: 200%;
  }

  i.glyphicon {
    top:2.5px;
  }

  #dropdown-app {
    width: 35px;
  }

  #dropdown-app img {
    width: 35px;
    display: block;
    margin: 0 auto;
  }

  #dropdown-app p {
    color: black;
  }


  .dialog {
    width: 100%;
    height: 670px;
    margin-top: -40px;
    background-color: rgba(0,0,0,0.30);
    margin-left: auto;
    margin-right: auto;
    position: absolute;
    z-index: 1000;
    left: 0;
    right: 0;
  }

  .dialog-bg {
    background-color: #FBFBFB;
    width: 360px;
    height: 189px;
    margin-top:100px;
    margin-left: auto;
    margin-right: auto;
    -webkit-border-radius: 7px; /* Saf3-4, iOS 1-3.2, Android <1.6 */
    -moz-border-radius: 7px; /* FF1-3.6 */
    border-radius: 7px; /* Opera 10.5, IE9, Saf5, Chrome, FF4, iOS 4, Android 2.1+ */
  }

  .dialog-title {
    width: 360px;
    height: 30px;
    padding-top: 12px;
    background-color: #FFFFFF;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#FFFFFF), to(#E9E9E9)); /* Saf4+, Chrome */
    background-image: -webkit-linear-gradient(top, #FFFFFF, #E9E9E9); /* Chrome 10+, Saf5.1+, iOS 5+ */
    background-image: -moz-linear-gradient(top, #FFFFFF, #E9E9E9); /* FF3.6 */
    background-image: -ms-linear-gradient(top, #FFFFFF, #E9E9E9); /* IE10 */
    background-image: -o-linear-gradient(top, #FFFFFF, #E9E9E9); /* Opera 11.10+ */
    background-image: linear-gradient(top, #FFFFFF, #E9E9E9);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#FFFFFF', EndColorStr='#E9E9E9'); /* IE6-IE9 */
    border-top-left-radius: 7px;
    border-top-right-radius: 7px;
    border-bottom: 1px solid #FFFFFF;
    color: #434343;
    text-shadow: 1px 1px 1px #ffffff;
    filter: dropshadow(color=#ffffff, offx=1, offy=1);
    text-align: center;
    font-weight: bold;
    font-size: 14px;
    cursor: default;
  }

  .dialog-description {
    width: 320px;
    margin-left: auto;
    margin-right: auto;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 18px;
    color: #555555;
    font-size: 13px;
    text-align: center;
    line-height: 24px;
    border-top: 1px solid #B9B9B9;
    cursor: default;
  }

  .dialog-buttons {
    width: 320px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
    text-align: center;
  }


  .button {
    font-size: 11px;
    font-weight: bold;
    line-height: 1;
  }

  .large.button {
    font-size: 13px;
    padding: 8px 14px 9px;
  }

  .blue.button {
    background-color: #4d7cb9;
    color: #fff;
    border: 1px solid #2a3b8c;
    padding-left: 35px;
  }

  #menu ul.dropdown-menu {
    left: 13px;
    top: 32px;
    width: 170px;
  }

  #menu ul {
    color: #6a6a6a;
    text-align: left;
  }

  #menu button.btn.btn-default {
    margin-top: 10px;
    padding: 1% 5%;
    font-size: 100%;
    box-shadow: none;
    background-color: #dcdcdc;
    border: solid 1px #dcdcdc;
    border-radius: 15px;
  }

  .image-min {
    /*left: 8%;*/
    /*position: absolute;*/
    /*margin-top: -14px;*/
    /* margin-left: 10%; */
    cursor: pointer;
    margin-right: 10px;
    border-radius: 50%;
    object-fit: cover;
    height: 50px;
    width: 50px;
    z-index: 1000;
  }

</style>

