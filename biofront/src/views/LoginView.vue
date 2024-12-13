<template>
  <div class="login">
    <h1>Login</h1>
    <form @submit.prevent="loginUser" class="login-form">
      <div class="form-group">
        <label for="username">Username:</label>
        <input
          type="text"
          v-model="username"
          id="username"
          required
          placeholder="Enter your username"
        />
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input
          type="password"
          v-model="password"
          id="password"
          required
          placeholder="Enter your password"
        />
      </div>

      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>

      <button
        type="submit"
        :disabled="isLoading"
      >
        {{ isLoading ? 'Logging in...' : 'Login' }}
      </button>

      <div class="register-link">
        Don't have an account?
        <router-link to="/register">Register here</router-link>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { Client } from '@/Client';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();
    const username = ref('');
    const password = ref('');
    const client = new Client();
    const errorMessage = ref('');
    const isLoading = ref(false);

    const loginUser = async () => {
      errorMessage.value = '';

      if (!username.value || !password.value) {
        errorMessage.value = 'Please enter both username and password';
        return;
      }

      isLoading.value = true;

      try {
        const body = {
          username: username.value,
          password: {
            value: password.value,
          },
        };

        const response = await client.postApiLogin(body);

        localStorage.setItem('token', response.token);
        localStorage.setItem('username', username.value);

        router.push('/');
      } catch (error: any) {
        if (error.status === 401) {
          errorMessage.value = 'Invalid username or password';
        } else if (error.status === 404) {
          errorMessage.value = 'User not found';
        } else {
          errorMessage.value = 'An unexpected error occurred. Please try again.';
        }

        console.error('Login failed:', error);
      } finally {
        isLoading.value = false;
      }
    };

    return {
      username,
      password,
      loginUser,
      errorMessage,
      isLoading
    };
  },
};
</script>

<style scoped>
.login {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.login-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

input {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
}

button {
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.error-message {
  color: red;
  margin-bottom: 15px;
}

.register-link {
  margin-top: 15px;
  text-align: center;
}
</style>
