<template>
  <div class="navbar">
        <img src="../images/logo.png" class="logo">
      <div class="menu">
        <router-link class="nav-link" to="/">Accueil</router-link>
        <router-link class="nav-link" to="/evenement">Événements</router-link>
        <router-link class="nav-link" to="/membre">Membres</router-link>
        <router-link class="nav-link" to="/lieux">Lieux</router-link>

        <router-link class="nav-link" to="/connecte">Se connecter</router-link>
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
 height: 12%;
 display: flex;
 align-items: center;
}

.logo {
  width: 50px;
  margin-top: 10px;
  margin-left: 20px;
  cursor: pointer;
}

.menu {
  flex: 1;
  text-align: right;
}
.nav-link{
    list-style: none;
    display: inline-block;
    margin-right: 60px;
    text-decoration: none;
    color: #fff;
}

</style>
