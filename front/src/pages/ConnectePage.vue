<template>    
  <div>
    <HeaderComponentV2></HeaderComponentV2>
    <div class="login-page">
      <div class="login-form">
          <h2 class="connexion">Connexion</h2>
          <div class="p-field">
            <label for="username">Nom d'utilisateur</label>
            <input class="" id="username" v-model="nomUser" type="text" />
          </div>
          <div class="p-field">
            <label for="password">Mot de passe</label>
            <input class="password-input" id="password" v-model="mdp" type="password" />
          </div>
          <button @click="verifConnect()">Se Connecter</button>
          <button @click="sinscrire()">S'inscrire</button>

        </div>  
   </div>
   <ModalInscriptionMember v-if="store.getters.getOpenModalUserAdd"></ModalInscriptionMember>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import HeaderComponentV2 from '@/components/HeaderComponentV2.vue';
import ModalInscriptionMember from '@/components/ModalInscriptionMember.vue';

import { useStore } from 'vuex';
import axios from 'axios';
export default {
  components: { HeaderComponentV2, ModalInscriptionMember },
    name: 'ConnectePage',
    setup() {  
              const store = useStore();
            const listMembress = ref([]);

        const nomUser = ref('')
        const mdp = ref('')
        const trouveUser = ref(false)

        const verifConnect = async () => {
              if (nomUser.value != "" && mdp.value != "") {
                for (let index = 0; index < store.state.listMembres.length; index++) {
                  console.log("frfrfrfr");
                  if (store.state.listMembres[index].nom ==nomUser.value && store.state.listMembres[index].password ==mdp.value ) {
                    trouveUser.value =true 
                    console.log("touvveee");
                  }
                  console.log("deded");
                  
                }
                console.log("dede");
              }
              console.log("cfrfr" + nomUser.value + mdp.value);
           }
        /*const connecter =  () => {
        if (nomCompte.value === nomCompteConst && mdp.value === mdpConst) {
          console.log('Connexion réussie');
          router.push('/admin'); // à changer 
        } else {
          console.log('Échec de la connexion');
        }
        };

        */
         const sinscrire =  () => {
            store.commit('toogleOpenModalUserAddTrue');
        };
           const getMembre = async () => {
  try {
    console.log("kkfrf")
    const response = await axios.get(`http://localhost:8080/membres`);
    const membres = response.data;
    
   membres.forEach(membre => {
      listMembress.value.push(membre);
   });
    //store.listMembres.push(listMembress)
    store.state.listMembres = listMembress.value;

    console.log('Noms des aliments par type:', listMembress.value);
    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }

};
        

    onMounted( async () => {
        await getMembre()
        console.log('frfr');
      });
  
    
      
    
      return {
        nomUser,
        verifConnect,
        store,
        mdp,
        sinscrire,
      };
    }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  .connexion{
    margin-bottom: 15px;
  }
  
  .login-form {
    max-width: 400px;
    width: 100%;        
    color: #ffffff;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  .p-field {
    margin-bottom: 20px;

  }
  label{
    margin-right: 20px;
  }
  .password-input{
    margin-left: 25px;
  }
  button {
    background-color: #42b883;
        margin-top: 20px;

    margin-left: 130px;
    color: #ffffff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
  }
</style>
<!-- LoginPage.vue -->


