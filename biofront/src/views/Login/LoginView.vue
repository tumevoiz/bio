<template>
  <div class="login-container">
    <div class="login-box">
      <h1>BIO PROJEKT</h1>
      <form @submit.prevent="loginUser">
        <label for="username">Login:</label>
        <input
          type="text"
          v-model="username"
          id="username"
          required
          placeholder="Wpisz swój login"
        />

        <label for="password">Hasło:</label>
        <input
          type="password"
          v-model="password"
          id="password"
          required
          placeholder="Wpisz swoje hasło"
        />

        <button type="submit" class="btn-submit">Zaloguj się</button>
      </form>
      <p class="register-link">
        Nie masz konta? <router-link to="/register">Zarejestruj się</router-link>
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import {ApiService} from "@/components/client";

const router = useRouter()
const username = ref('')
const password = ref('')

import { OpenAPI } from '@/components/client';

const loginUser = async (): Promise<void> => {
  try {
    // Możesz dodać logikę logowania tutaj
    let response = await ApiService.postApiLogin({
      username: username.value,
      password: {
        value: password.value
      }
    })
    console.log(response)

    localStorage.setItem('token', response["token"])
    router.push({ path: '/' })
  } catch (error) {
    console.error('Login failed:', error)
    alert('Invalid credentials')
  }
}
</script>

<style scoped>
@import 'LoginView.css';
</style>
