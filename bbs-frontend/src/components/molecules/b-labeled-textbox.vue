<template>
  <div class="col-wrapper">
  <b-label :labelFor="labeledTextAreaId">
    <slot/>
  </b-label>
  <b-textbox
    :textAreaId="labeledTextAreaId"
    textAreaPlaceholder="Place your post message here."
    @input="updateValue"
    :value="modelValue"
  >
  </b-textbox>
</div>
</template>
<script setup lang="ts">
import bTextbox from '../atoms/b-textbox.vue';
import bLabel from '../atoms/b-label.vue';
import { defineProps, defineEmits } from 'vue';

defineProps({
  labeledTextAreaId: {
    type: String,
    required: true,
  },
  modelValue: {
    type: String,
  }
})

const emit = defineEmits(['update:modelValue'])

const updateValue = (e: Event) => {
  emit('update:modelValue', (e.target as HTMLInputElement).value)
};
</script>
<style scoped>
</style>