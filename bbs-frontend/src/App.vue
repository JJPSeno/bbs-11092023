<template>
  <HomePage/>
</template>
<script setup lang="ts">
import HomePage from './components/pages/HomePage.vue';
import { usePost } from './stores/usePost';
import { onMounted } from 'vue';
const postStore = usePost()

onMounted(() => {
  postStore.populatePosts()
})

</script>
<style scoped>
</style>
