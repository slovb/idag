<script setup lang="ts">
import TheDay from '../components/TheDay.vue'

const props = defineProps<{
  day?: string
}>()

function today(): string {
  const now = new Date()
  return now.toISOString().split('T')[0]
}
const key = props.day ?? today()
</script>

<template>
  <main><TheDay :day="key" /></main>
</template>
