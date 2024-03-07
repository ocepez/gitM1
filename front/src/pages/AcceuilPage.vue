<template>
  <div class="acceuilPage">
    <HeaderComponentV2></HeaderComponentV2>
    <div class="row">
        <div class="col">
            <h1>Asso-Saha</h1>
            <p class="description">Le lorem ipsum est, en imprimerie, une suite de mots sans signification utilisée à titre provisoire pour calibrer une mise en page, le texte définitif venant remplacer le faux-texte dès qu'il est prêt ou que la mise en page est achevée. Généralement, on utilise un texte en faux latin, le Lorem ipsum ou Lipsum.</p>
            <button @click="redirigerVersConnecte" class="inscrire">S'authentifier</button>
        </div>
        <div class="containerCards">
            <CardComponentMenu v-for="index in 4" :key="index" :index="index"/> 
        </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import CardComponentMenu from '../components/CardComponentMenu.vue'
import HeaderComponentV2 from '../components/HeaderComponentV2.vue'

export default {
    components: { CardComponentMenu, HeaderComponentV2 },
    name: 'AccueilPage',
    setup() {  
        const router = useRouter();

        const redirigerVersConnecte = () => {
            router.push('/connecte');
        }

        return {
            redirigerVersConnecte,
        };
    }
}
</script>

<style scoped>
.acceuilPage {
    width: 100%;
    height: 100vh;
    background-image: linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.7)), url(../images/background.png);
    background-position: center;
    background-size: cover;
}

.row {
    margin-left: 40px;
    display: flex;
    height: 88%;
    align-items: center;
}

.col {
    flex-basis: 50%;
}

h1 {
    color: #fff;
    font-size: 100px;
}

p {
    color: #fff;
    font-size: 13px;
    line-height: 15px;
}

.containerCards {
    flex-basis: 50%;
}

.description {
    font-size: 15px;
}

.inscrire {
    font-size: 18px;
    margin-top: 20px;
    background-color: #42b883;
    color: #ffffff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
}
</style>
