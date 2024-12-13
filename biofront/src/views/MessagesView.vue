<template>
  <div class="main-container">
    <q-layout view="hHh Lpr lff" container>
      <q-header elevated class="bg-primary text-white">
        <q-toolbar>
          <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

          <q-toolbar-title>
            <q-avatar>
              <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
            </q-avatar>
            Czat
          </q-toolbar-title>
        </q-toolbar>
      </q-header>

      <q-drawer
        v-model="leftDrawerOpen"
        elevated
        show-if-above
        side="left"
        bordered
        class="bg-black"
      >
        <q-scroll-area class="fit">
          <q-list bordered>
            <q-item clickable v-ripple v-for="channel in channels">
              <q-item-section avatar>
                <q-icon color="primary" name="message" />
              </q-item-section>
              <q-item-section @click="() => onChannelItemClick(channel.id)">
                {{ channel.name }}
              </q-item-section>
            </q-item>
          </q-list>
        </q-scroll-area>
      </q-drawer>

      <q-page-container>
        <q-page class="q-pa-md d-block" padding>
          <router-view />
        </q-page>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script setup lang="ts">
import { Channel, Client } from '@/Client'
import { onMounted, ref, type Ref } from 'vue'
import { useRouter } from 'vue-router'

const leftDrawerOpen = ref(false)
const router = useRouter()
const client = new Client()
const channels: Ref<Channel[]> = ref([])

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value
}

onMounted(async () => {
  channels.value = await client.getApiChannels()
})

const onChannelItemClick = (id: string) => {
  leftDrawerOpen.value = false
  router.push({ name: 'channel', params: { id } })
}
</script>

<style lang="css" scoped>
.main-container {
  min-height: 100vh;
  min-width: 100vw;
}
</style>
