<template>
  <div class="navbar">
    <div class="container">
      <div class="brand">AssociationApp</div>
      <div class="menu">
        <router-link class="nav-link" to="/">Accueil</router-link>
        <router-link class="nav-link" to="/evenement">Événements</router-link>
        <router-link class="nav-link" to="/membre">Membres</router-link>
        <router-link class="nav-link" to="/connecte">Se connecter</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted } from 'vue';

export default {
  name: 'HeaderComponent', 
  setup() {
    onMounted(() => {
      console.log('frfr');
    });
  }
}
</script>

<style scoped>
.navbar {
  background-color: #42b883;
  color: #ffffff;
  padding: 10px 0;
}

.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.brand {
  font-size: 1.5em;
}

.menu .nav-link {
  text-decoration: none; /* Supprimer le souligné */
  color: #ffffff;
  cursor: pointer;
  font-size: 1em;
  margin-left: 10px;
}
</style>
