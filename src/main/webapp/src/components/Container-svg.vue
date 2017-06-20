<template>
  <div>
    <div class="header"><br>
      <h1>Spike SVG</h1></div>
    <form @submit.prevent="addCircle">
      <center>
        <div class="col-lg-2 col-md-2 col-md-offset-4 col-lg-offset-4">
          <input class="form-control col-lg-3" type="text" v-model="label">
        </div>
        <button class="col-lg-1 col-md-1 btn btn-default">Ajouter !</button>
      </center>
    </form>

    <div class="svg-content" id="test">
      <svg version="1.1" viewBox="0 0 1250 1250" preserveAspectRatio="xMinYMin meet">
        <g v-for="link in links">
          <line  @mouseover="selectedlink = link;showCross = true;" :x1="getPositionXById(link.skill1.id)"
                :y1="getPositionYById(link.skill1.id)"
                :x2="getPositionXById(link.skill2.id)"
                :y2="getPositionYById(link.skill2.id)" style="stroke:black;stroke-width:3"/>
        </g>
        <g v-for="(skill,i) in skills">
          <customCircle :id="skill.id" :cx="positionX(i)" :cy="positionY(i)" :content="skill.label"
                        @click="selectSkill(skill)"/>
        </g>
        <CloseCross v-show="showCross" style="cursor: pointer;"@click="removeLink(selectedlink)":x1="linkPositionX()" :y1="linkPositionY()"></CloseCross>
      </svg>
    </div>
  </div>
</template>

<script>
  import CustomCircle from "./customcircle"
  import CloseCross from "./CloseCross.vue"

  export default {
//    name: 'container-svg',
    data () {
      return {
        selectedSkill: {
            skill1:'',
            skill2:''
        },
        skills: [],
        selectedlink: '',
        label: '',
        showCross : false,
        text: [],
        posX: 100,
        posY: 100,
        numberOfCircle: 0,
        row: 0,
        links:[]
      }
    },
    mounted(){
      this.getAllSkills();
      this.getAllLinks();
    },
    methods: {
      removeLink(link){
        this.$http.post('http://localhost:8083/api/removelink', link).then(
          response => {
            console.log(response);
            this.getAllLinks();
          }, response => {
            console.log(response);
          })
      },
      linkPositionX(){
        if (this.selectedlink != '') {
          var x1 = parseFloat(this.getPositionXById(this.selectedlink.skill1.id))-3;
          var x2 = parseInt(this.getPositionXById(this.selectedlink.skill2.id))-3;
          console.log("x1= " +x1);
          console.log("x2= "+x2);
          var total = (x1 + x2) / 2;
          console.log(total);
          return total
        }
        else
          return 0
      },
      linkPositionY(){
        if (this.selectedlink != '') {
          var y1 = parseInt(this.getPositionYById(this.selectedlink.skill1.id)) - 3;
          var y2 = parseInt(this.getPositionYById(this.selectedlink.skill2.id)) - 3;
          console.log("y1= " +y1);
          console.log("y2= "+y2);
          var somme = y1+y2;
          console.log(somme/2);
          return somme/2
        }
        else
          return 0;
      },

      waitForElementToDisplay(selector, time, position){
        let self = this;
        if(document.getElementById(selector)!=null) {
          return document.getElementById(selector).getElementsByTagName("circle")[0].getAttribute(position);
        }
        else {
          setTimeout(function() {
            self.waitForElementToDisplay(selector, time);
          }, time);
        }
      },

      getPositionXById(id){
        return this.waitForElementToDisplay(id,0,"cx");
      },

      getPositionYById(id){
       return this.waitForElementToDisplay(id,0,"cy");
      },
      selectSkill(skill){
        if (this.selectedSkill.skill1 == '')
          this.selectedSkill.skill1 = skill;
        else {
          this.selectedSkill.skill2 = skill;
          this.$http.post('http://localhost:8083/api/addlink', this.selectedSkill).then(
          response => {
                console.log(response);
          }, response => {
            console.log(response);
          }).then(
            function () {
              this.selectedSkill = {
                skill1:'',
                skill2:''
              };
              this.getAllSkills();
              this.getAllLinks();
            }
          );
        }
      },
      positionX(integ){
          console.log()
        return this.posX + ((integ) % 8) * 150;
      },
      positionY(integ){
        return this.posY + Math.floor((integ) / 8) * 150;
      },
      addCircle() {
        this.addSkill();
        this.text.push(this.text1);
        this.numberOfCircle++;
        this.label = "";
      },
      addSkill(){
        var skill = {"label": this.label};
        this.$http.post('http://localhost:8083/api/addskill/', skill).then(response => {
          this.getAllSkills();
        }, response => {
          console.log(response);
        });
      },
      getAllSkills(){
        this.$http.get("http://localhost:8083/api/skills/").then(response => {
          this.skills = response.body;
          this.skills.sort(function (a, b) {
            return (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0);
          });
        }, response => {
          console.log(response);
        });
      },
      getAllLinks(){
        this.showCross=false;
        this.$http.get("http://localhost:8083/api/links/").then(response => {
          this.links = response.body;
          if(this.selectedlink=='')
              this.showCross =false;
          this.links.sort(function (a, b) {
            return (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0);
          });
        }, response => {
          console.log(response);
        });
      }
    },
    components: {customCircle: CustomCircle, CloseCross: CloseCross}

  }
</script>

<style>

  svg-content {
    display: inline-block;
    position: relative;
    width: 100%;
    padding-bottom: 100%;
    vertical-align: middle;
    overflow: hidden;
  }

  .defaultSize {
    font-size: 24px;
  }

  .smallSize {
    font-size: 17px;
  }

  body {
    color: #075338;
    margin: 0;
    font-family: 'Lato', sans-serif;
  }

  h1 {
    color: white;
    text-align: center;
    margin: 0;
  }

  .header {
    background-color: #09aa76;
    height: 80px;
    margin: 0px 0px 25px 0px;
  }

  .test {
    animation: mymove 0.5s 1;
  }

  @keyframes mymove {
    from {
      opacity: 0
    }
    to {
      opacity: 1
    }
  }
</style>
