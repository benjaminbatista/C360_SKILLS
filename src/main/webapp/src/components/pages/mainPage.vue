<template>
  <div>
  <HeaderSkills></HeaderSkills>
  <router-view></router-view>
  </div>
</template>

<script>
import HeaderSkills from '../layout/skillsHeader.vue'

  export default {
    components: { HeaderSkills: HeaderSkills},

  }
</script>
