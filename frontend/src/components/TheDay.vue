<script setup lang="ts">
const props = defineProps<{
  day: string
}>()
</script>

<template>TheDay {{ props.day }}</template>
