<template>
<div class="app"> 
    <router-view></router-view>

</div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
.app{
  margin: 0;
  padding: 0;
  width: 100%;
  background-image: linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.7)), url(./images/background.png);
  background-position: center;
  background-size: cover;

}
</style>
