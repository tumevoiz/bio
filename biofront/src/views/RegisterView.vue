<template>
  <div class="register">
    <h1>Register</h1>
    <form @submit.prevent="registerUser">
      <label for="username">Username:</label>
      <input type="text" v-model="username" id="username" required />

      <label for="password">Password:</label>
      <input type="password" v-model="password" id="password" required />

      <button type="submit">Register</button>
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

    const registerUser = async () => {
      try {
        // Tworzenie obiektu z danymi, które mają być wysłane w żądaniu
        const body = {
          username: username.value,
          password: {
            value: password.value,  // "value" wewnątrz obiektu password
          },
        };

        // Wywołanie endpointu /api/users z danymi w formacie JSON
        await client.postApiUsers(body);
        alert('User registered successfully!');
      } catch (error) {
        console.error('Registration failed:', error);
        alert('Error during registration');
      }
    };

    return {
      username,
      password,
      registerUser,
    };
  },
};
</script>
