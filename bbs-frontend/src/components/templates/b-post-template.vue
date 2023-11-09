<template>
  <header>
    <h1>JJPSENO BBS APP</h1>
  </header>
  <main>
    <h2>post here:</h2>
    <b-post-form />
    <hr>
    <b-post-list />
  </main>
</template>
<script setup lang="ts">
import bPostForm from '../organisms/b-post-form.vue';
import bPostList from '../organisms/b-post-list.vue';
</script>
<style scoped>
header {
  display: flex;
  justify-content: center;
}
hr{
  width: 130%;
  margin-bottom: 5px;
  margin-top: 10px;
  margin-left: -10%;
}
</style>