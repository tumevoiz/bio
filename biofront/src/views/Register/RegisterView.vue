<template>
  <div class="register-container">
    <div class="register-box">
      <h1>BIO PROJEKT</h1>
      <form @submit.prevent="registerUser">
        <label for="username">Login:</label>
        <input type="text" v-model="username" id="username" required placeholder="Wpisz swój login" />

        <label for="password">Hasło:</label>
        <input type="password" v-model="password" id="password" required placeholder="Wpisz swoje hasło" />

        <button type="submit" class="btn-submit">Zarejestruj się</button>
      </form>
      <p class="login-link">Masz już konto?<router-link to="/login">Zaloguj się</router-link></p>
    </div>
  </div>
</template>

<style>
@import 'RegisterView.css';
</style>


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
