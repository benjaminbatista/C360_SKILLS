<template>
  <g @click="handleClick()">
    <line  :x1="x1" :y1="y1+12"
          :x2="x1+12" :y2="y1"
          stroke="red"
          stroke-width="3"/>
    <line :x1="x1" :y1="y1"
          :x2="x1 + 12" :y2="y1+12"
          stroke="red"
          stroke-width="3"/>
  </g>
</template>

<script>
  import store from "../stores/indexStore"

  export default {
    props: ["x1", "y1"],
    data () {
      return {}
    },
    methods: {
        handleClick(){
            this.$emit('click');
        }
    }
  }

</script>

<style>



</style>
