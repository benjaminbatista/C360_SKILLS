<template>
  <div>
    <svg>
      <line x1="50" y1="50" :x2="lineABX" :y2="lineABY" style="stroke:black;stroke-width:2;" />
      <line x1="300" y1="50" :x2="lineBAX" :y2="lineBAY" style="stroke:black;stroke-width:2;" />
      <circle id="a" @click="checkLineA" cx="50" cy="50" r="50" fill="#09aa76" stroke="#075338" stroke-width="2"></circle>
      <circle id="b" @click="checkLineB" cx="300" cy="50" r="50" fill="#09aa76" stroke="#075338" stroke-width="2"></circle>

    </svg>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      lineBAY:'',
      lineABY:'',
      lineBAX:'',
      lineABX:'',
      skills:{}

    }
  },
  mounted(){
      this.addSkill();
      this.checkLineA();
      this.checkLineB();
  },

  methods:{
    checkLineA(){
      this.lineABY = document.getElementById('b').getAttribute('cy');
      this.lineABX = document.getElementById('b').getAttribute('cx')
    },
    checkLineB(){
        this.lineBAY = document.getElementById('a').getAttribute('cy');
        this.lineBAX = document.getElementById('a').getAttribute('cx')

    },
    addLink(){
        var skill =   {"id":2,"version":0,"label":"hoho","links":[],"collaborators":[]}
      this.$http.post('http://localhost:8083/api/addlink/1',skill).then(response => {

        // get body data
        this.skills = response.body;

      }, response => {
          console.log(response);
      });
    },
    addSkill(){
      var skill = {"label":this.label};
      this.$http.post('http://localhost:8083/api/addskill/',skill).then(response => {

        // get body data
        this.skills = response.body;

      }, response => {
        console.log(response);
      });
    }
  }
}
</script>

<style>



</style>
