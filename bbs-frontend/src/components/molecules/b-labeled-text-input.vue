<template>
  <div class="col-wrapper">
  <b-label :labelFor="labeledInputId">
    <slot/>
  </b-label>
  <b-input
  :inputId="labeledInputId"
  :inputClass="labeledTextInputClass"
  inputType="text" 
  :inputPlaceholder="labeledTextInputPlaceholder"
  @input="updateValue"
  :value="modelValue"
  />
</div>
</template>
<script setup lang="ts">
import bInput from '../atoms/b-input.vue';
import bLabel from '../atoms/b-label.vue';
import { defineProps, defineEmits } from 'vue';

defineProps({
  labeledInputId: {
    type: String
  },
  labeledTextInputClass: {
    type: String
  },
  labeledTextInputPlaceholder: {
    type: String
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