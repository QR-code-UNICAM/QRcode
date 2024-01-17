<template>
  <div>

    <div class="carousel">
      <div class="img-list">
        <img
          v-for="img in imgList"
          :key="img"
          :src="img"
        >
      </div>

      <div class="buttons">
        <button @click="prev">&lt;</button>
        <button @click="next">&gt;</button>
      </div>

    </div>

  </div>
</template>


<script>
export default {
  created() {
    console.log(this.$route)
  },
  data() {
    return {
      email:'',
      imgList: [
        'url1',
        'url2',
        // ...
      ],
      currentIndex: 0
    }
  },

  methods: {

    prev() {
      this.currentIndex--
      if(this.currentIndex < 0) {
        this.currentIndex = this.imgList.length - 1
      }
    },

    next() {
      this.currentIndex++
      if(this.currentIndex > this.imgList.length - 1) {
        this.currentIndex = 0
      }
    }

  }

}
</script>


<style>
/* 样式 */
</style>
