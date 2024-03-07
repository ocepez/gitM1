<template>
 <div class="navbar">
      <div class="container">
        <div class="brand">MiammApp</div>
        <div class="menu">
          <button @click="navigateTo('/')">Accueil</button>
          <button @click="navigateTo('/menu')">Menu du jour</button>
          <button @click="navigateTo('/connect')">Se connecter</button>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'HelloWorld', 

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
  
  .menu button {
    background: none;
    border: none;
    color: #ffffff;
    cursor: pointer;
    font-size: 1em;
    margin-left: 10px;
  }
  
</style>
