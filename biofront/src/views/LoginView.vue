<template>
  <div class="login">
    <h1>Login</h1>
    <form @submit.prevent="loginUser">
      <label for="username">Username:</label>
      <input type="text" v-model="username" id="username" required />

      <label for="password">Password:</label>
      <input type="password" v-model="password" id="password" required />

      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';
import { Client } from '@/Client';

export default {
  setup() {
    const username = ref('');
    const password = ref('');
    const client = new Client();

    const loginUser = async () => {
      try {
        // Tworzenie obiektu z danymi, które mają być wysłane w żądaniu
        const body = {
          username: username.value,
          password: {
            value: password.value,  // "value" wewnątrz obiektu password
          },
        };

        // Wywołanie endpointu /api/login z danymi w formacie JSON
        await client.postApiLogin(body);
        alert('Logged in successfully!');
      } catch (error) {
        console.error('Login failed:', error);
        alert('Invalid credentials');
      }
    };

    return {
      username,
      password,
      loginUser,
    };
  },
};
</script>
