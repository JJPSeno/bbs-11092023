import { reactive, ref } from 'vue'
import { defineStore } from 'pinia'

export const usePost = defineStore('post', () => {
  console.log("store loaded");
  const testVar = "test";
  const testRefVar = ref("test");

  const postsStore = reactive({posts:[]});

  const postForm = reactive({
    postMessage: '',
    postPassword: '',
  })

  const clearForm = () => {
    postForm.postMessage = ''
    postForm.postPassword = ''
  }

  const populatePosts = () =>{
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    };
    const fetchedPosts = fetch("http://localhost:9000/posts", requestOptions)
      .then(response => response.json())
      .then(data => {
        postsStore.posts = data
        //console.log(posts.value)
        populatePosts()
      })
      .catch(error => console.error('Error fetching data:', error));
  }

  const uploadImage = () => {
    console.log("hit upload image")
  }

  const submitPost = () => {
    console.log(`submit post started. postMessage: ${postForm.postMessage} password: ${postForm.postPassword}`)
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ postMessage: postForm.postMessage, postPassword: postForm.postPassword })
    };
    fetch("http://localhost:9000/posts", requestOptions)
      .then(response => {
        clearForm()
        response.toString()
      })
      .catch(error => console.error('Error fetching data:', error));
  }

  return {
    testVar,
    testRefVar,
    postsStore,
    postForm,
    populatePosts,
    uploadImage,
    submitPost,
  }
})