<template>
  <g>
    <circle @click="handleClick()" :id="cx+''+cy" :cx="cx" :cy="cy" r="50" :fill="fill" :stroke="stroke" stroke-width="2"></circle>
    <text @click="handleClick()" text-anchor="middle" :x="cx" :class="mySize" :y="cy+8" style="fill: rgba(0,0,0,0.52);">{{content}}</text>
  </g>
</template>

<script>
  import store from "../stores/indexStore"

  export default {
    props:["cx","cy", "content","fill","stroke"],
    data () {
      return {
        cx1: "",
        cy1: "",
        cx2: "",
        cy2: "",
        cx3: "",
        cy3: "",
        cyLine1:"",
        cxLine1:"",
        cyLine2:"",
        cxLine2:"",
        cyLine3:"",
        cxLine3:"",
      }
    },
    computed: {
      mySize(){
//        console.log(this.content.length);
//        if(this.content.length>4) {
//          return 'smallSize'
//        }
//        else return 'defaultSize';
      }
    },
    methods: {
      handleClick(){
        this.$emit('click');
      },
      calculatePosition(cxLine,cyLine){
        if(store.state.cx == this.cx && store.state.cy == this.cy + 150){ //haut
          store.state.cy = store.state.cy - 50;
          cxLine = this.cx;
          cyLine = this.cy + 50;
        }
        else if(store.state.cx == this.cx && store.state.cy == this.cy - 150){ //bas
          store.state.cy = store.state.cy + 50;
          cxLine = this.cx;
          cyLine = this.cy - 50;
        }
        else if(store.state.cx == this.cx + 150 && store.state.cy == this.cy){ //gauche
          store.state.cx = store.state.cx - 50;
          cxLine = this.cx + 50;
          cyLine = this.cy;
        }
        else if(store.state.cx == this.cx - 150 && store.state.cy == this.cy){ //droit
          store.state.cx = store.state.cx + 50;
          cxLine = this.cx - 50;
          cyLine = this.cy;
        }else if(store.state.cx == this.cx + 150 && store.state.cy == this.cy + 150){ //gauche haut
          console.log("HELLO gauche haut");
          store.state.cx = store.state.cx - 50/Math.sqrt(2);
          store.state.cy = store.state.cy - 50/Math.sqrt(2);
          cxLine = this.cx + 50/Math.sqrt(2);
          cyLine = this.cy + 50/Math.sqrt(2)
          console.log(store.state.cx + '  ' +store.state.cy);
        }else if(store.state.cx == this.cx - 150 && store.state.cy == this.cy + 150){ //droit haut
          console.log("HELLO droit haut");
          store.state.cx = store.state.cx + 50/Math.sqrt(2);
          store.state.cy = store.state.cy - 50/Math.sqrt(2);
          cxLine = this.cx - 50/Math.sqrt(2);
          cyLine = this.cy + 50/Math.sqrt(2);
          console.log(store.state.cx + '  ' +store.state.cy);
        }else if(store.state.cx == this.cx + 150 && store.state.cy == this.cy - 150){ //gauche bas
          console.log("HELLO gauche bas");
          store.state.cx = store.state.cx - 50/Math.sqrt(2);
          store.state.cy = store.state.cy + 50/Math.sqrt(2);
          cxLine = this.cx + 50/Math.sqrt(2);
          cyLine = this.cy - 50/Math.sqrt(2);
          console.log(store.state.cx + '  ' +store.state.cy);
        }else if(store.state.cx == this.cx - 150 && store.state.cy == this.cy - 150){ //droit bas
          console.log("HELLO droit bas");
          store.state.cx = store.state.cx + 50/Math.sqrt(2);
          store.state.cy = store.state.cy + 50/Math.sqrt(2);
          cxLine = this.cx - 50/Math.sqrt(2);
          cyLine = this.cy - 50/Math.sqrt(2);
          console.log(store.state.cx + '  ' +store.state.cy);
        }else{ //ne pas generer le trait
          store.state.cx = 0;
          store.state.cy = 0;
          cxLine = 0;
          cyLine = 0;
        }
        store.state.cx1 = cxLine;
        store.state.cy1 = cyLine;
        var resulat = [];
        resulat.push(cxLine);
        resulat.push(cyLine);
        resulat.push(store.state.cx);
        resulat.push(store.state.cy);
        return resulat;
      },
      checkLine(){
        if(store.state.cx == "" && store.state.cy == ""){
          var el = document.getElementById(this.cx+''+this.cy);
          store.state.cx = this.cx;
          store.state.cy = this.cy;
          el.style.fill="#075338";
          el.style.stroke="#09aa76"
        }
        else {
          var el = document.getElementById(store.state.cx+''+store.state.cy);
          if (this.cx1=="" && this.cy1==""){
            el.style.fill="#09aa76";
            el.style.stroke="#075338";
            var tmp = this.calculatePosition(this.cxLine1,this.cyLine1);
            this.cxLine1 = tmp[0];
            this.cyLine1 = tmp[1];
            this.cx1 = tmp[2];
            this.cy1 = tmp[3];
            store.state.cx = '';
            store.state.cx1 = '';
            store.state.cy = '';
            store.state.cy1 = '';
          }
          else if (this.cx2=="" && this.cy2==""){
            var tmp = this.calculatePosition(this.cxLine2,this.cyLine2);
            this.cxLine2 = tmp[0];
            this.cyLine2 = tmp[1];
            this.cx2 = tmp[2];
            this.cy2 = tmp[3];
            store.state.cx = '';
            store.state.cx1 = '';
            store.state.cy = '';
            store.state.cy1 = '';
          }
          else if (this.cx3=="" && this.cy3==""){
            var tmp = this.calculatePosition(this.cxLine3,this.cyLine3);
            this.cxLine3 = tmp[0];
            this.cyLine3 = tmp[1];
            this.cx3 = tmp[2];
            this.cy3 = tmp[3];
            store.state.cx = '';
            store.state.cx1 = '';
            store.state.cy = '';
            store.state.cy1 = '';
          }
        }
      }
    }
  }
</script>

<style>



</style>
