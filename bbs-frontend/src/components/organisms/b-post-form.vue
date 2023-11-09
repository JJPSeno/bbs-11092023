<template>
<form name="postForm" method="POST" @submit.prevent="postStore.submitPost">
  <b-labeled-text-input labeledInputId="postPassword" v-model="postStore.postForm.postPassword">
  post password (for deletion)
  </b-labeled-text-input> 
  <b-labeled-textbox labeledTextAreaId="postMessage" labeledTextInputClass="form-text-area" v-model="postStore.postForm.postMessage">
    post message
  </b-labeled-textbox>
  <b-button
    btnClass="button-form"
    btnType="button"
    btnName="image-upload"
    @click="postStore.uploadImage"
    >
    upload image
  </b-button>
  <b-button
    btnClass="button-form"
    btnType="submit"
    btnName="post-submit">
    submit post
  </b-button>
</form>
</template>
<script setup lang="ts">
  import bLabeledTextInput from '../molecules/b-labeled-text-input.vue';
  import bLabeledTextbox from '../molecules/b-labeled-textbox.vue';
  import bButton from '../atoms/b-button.vue';
  import {usePost} from '../../stores/usePost';
  const postStore = usePost()
</script>
<style scoped>
form {
  display: flex;
  flex-direction: column;
}
</style>