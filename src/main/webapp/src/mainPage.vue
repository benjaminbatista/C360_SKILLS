<template>
  <div>
  <HeaderSkills></HeaderSkills>
  <router-view></router-view>
  </div>
</template>

<script>
import HeaderSkills from './components/HeaderComponent/HeaderSkills.vue'

  export default {
    components: { HeaderSkills: HeaderSkills},
  }
</script>
