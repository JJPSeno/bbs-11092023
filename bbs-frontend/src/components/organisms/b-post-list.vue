<template>
  <ul>
    <li v-for="(post, index) in postStore.postsStore.posts">
      {{ post.message }}
    </li> 
  </ul>
</template>
<script setup lang="ts">
  import {usePost} from '../../stores/usePost';
  const postStore = usePost()

</script>
<style scoped>

</style>