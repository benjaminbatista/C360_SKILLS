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
        <g v-for="(skill,i) in skills">
          <customCircle :id="skill.id" :cx="positionX(i)" :cy="positionY(i)" :content="skill.label"
                        @click="selectSkill(skill)"/>
        </g>
        <g v-for="skill in skills">
          <line  v-for="link in skill.links" :x1="getPositionXById(skill.id)" :y1="getPositionYById(skill.id)"
                  :x2="getPositionXById(link.id)"
                  :y2="getPositionYById(link.id)" style="stroke:rgb(255,0,0);stroke-width:2"/>
        </g>
      </svg>
    </div>
  </div>
</template>

<script>
  import CustomCircle from "./customcircle"

  export default {
//    name: 'container-svg',
    data () {
      return {
        selectedSkill: '',
        skills: [],
        label: '',
        text: [],
        posX: 100,
        posY: 100,
        numberOfCircle: 0,
        row: 0,
        x:'',
        y:'',
        z:'',
      }
    },
    mounted(){
      this.getAllSkills();
    },
    methods: {

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
        if (this.selectedSkill == '')
          this.selectedSkill = skill;
        else {
          this.$http.post('http://localhost:8083/api/addlink/' + skill.id, this.selectedSkill).then(response => {
          }, response => {
            console.log(response);
          }).then(
            function () {
              this.selectedSkill = '';
              this.getAllSkills();
            }
          );
        }
      },
      positionX(integ){
        return this.posX + ((integ) % 8) * 150 + 50;
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
        }, response => {
          console.log(response);
        });
      }
    },
    components: {customCircle: CustomCircle}

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
