<template>
  <div class="row" v-if="showComponent">
    <div class="col">
      <q-chat-message
        v-for="mu in messagesWithUser"
        :text="[mu.message.message]"
        :name="[mu.user.username]"
      />
    </div>
  </div>
  <q-page-sticky expand position="bottom">
    <div style="width: 100%">
      <q-input
        v-model="text"
        filled
        type="textarea"
        class="bg-accent text-white"
        style="width: 100%;"
        @keyup.enter="handleEnter"
      />
    </div>
  </q-page-sticky>
</template>

<script setup lang="ts">
import {onMounted, ref, type Ref} from 'vue'
import {ApiService, type Message, OpenAPI, type UserResponse} from "@/components/client";
import type {MessageWithUser} from "@/components/domain/MessageWithUser.tsx";
import {useRoute} from "vue-router";

const messagesWithUser: Ref<MessageWithUser[]> = ref([])
const text = ref("")
const showComponent = ref(true)
const route = useRoute()

onMounted(async () => {
  setInterval(async () => {
    await refreshMessages()
  }, 2000);
})

const refreshMessages = async() => {
  let channelID = route.params.id
  OpenAPI.TOKEN = localStorage.getItem('token') as string
  console.log(OpenAPI.TOKEN)
  let messages = await ApiService.getApiMessages(channelID as string)

  messagesWithUser.value = await joinUserWithMessages(messages)
}

const handleEnter = async () => {
  let channelID = route.params.id
  await ApiService.postApiMessages({
    message: text.value,
    channelId: channelID as string
  })
  text.value = ""
}

async function getUserInfoFromMessage(message: Message): Promise<UserResponse> {
  const resp = await ApiService.getApiUsers(message.userId)
  return {username: resp.username};
}

const joinUserWithMessages = async (messages: Message[]): Promise<MessageWithUser[]> => {
  const p = await Promise.all(messages.map(async msg => {
    let user = await getUserInfoFromMessage(msg)
    return {
      message: msg,
      user: user
    } as MessageWithUser;
  }))

  return p
}

</script>
