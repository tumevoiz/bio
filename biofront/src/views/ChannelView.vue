<template>
  <div class="row">
    <div class="col">
      <q-chat-message
        v-for="message in messages"
        :text="[message.content]"
        :sent="currentUserId === message.userId"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const currentUserId = '1'
const messages = ref([
  { content: 'test', id: '123', userId: '1' },
  { content: 'test', id: '124', userId: '2' },
])
</script>
