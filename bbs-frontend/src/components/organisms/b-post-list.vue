<template>
    <div class="list-item" v-for="(post, index) in postStore.postsStore.posts">
      {{ post.message }}
    </div>
</template>
<script setup lang="ts">
  import {usePost} from '../../stores/usePost';
  const postStore = usePost()

</script>
<style scoped>
  .list-item{
    margin: 5px 0px;
  }
</style>