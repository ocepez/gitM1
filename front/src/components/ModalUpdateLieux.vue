<template>
  <!-- Modal Overlay -->
  <div class="modal-overlay">
    <!-- Modal Content -->
    <div class="modal-content">
      <!-- Close Button -->
      <span @click="closeModalUpdateUser()" class="close-btn">&times;</span>
      <!-- Modal Body -->
      <div class="modal-body">
        <!-- Content Here -->
        <h2>Mise à jour d'un lieux </h2>
        <form @submit.prevent="updatedLieux">
    
          <div class="form-group">
            <label for="name">Nom:</label>
            <input type="text" id="nom" required v-model="nom">
          </div>
          <div class="form-group">
            <label for="age">Adresse:</label>
            <input type="text" id="adresse" required v-model="adresse">
          </div>
          <div class="form-group">
            <label for="city">Capacité:</label>
            <input type="text" id="capacite" required v-model="capacite">
          </div>
        
          <button @click="updatedLieux()" type="submit">Mettre à jour</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
export default {
  name: 'ModalComponent', 
   props: {
    idMembre: {
      type: Number,
      required: true
    }
  },  
 

  setup(props) {
    const store = useStore();
    const nom = ref('');
    const adresse = ref('');
    const capacite = ref('');
            const listLieuxx = ref([]);
            const lieu= ref();


    const closeModalUpdateUser = () => {
      store.commit('toogleOpenModalUserUpdateFalse');
    }


    const updatedLieux = async () => {
      const updatedLieux = {
        nom: nom.value,
        adresse: adresse.value,
        capacite: capacite.value,
      };

      try {
        // Effectuer la requête PUT ou PATCH avec Axios
        const response = await axios.put(`http://localhost:8002/lieux/${props.idMembre}`, updatedLieux);
        console.log('Réponse du serveur :', response.data);

        // Réinitialiser les champs du formulaire après la soumission
        nom.value = '';
        adresse.value = '';
        capacite.value = '';

        // Fermer la modal
          await getLieux();

        closeModalUpdateUser();
      } catch (error) {
        console.error('Erreur lors de la mise à jour de l\'utilisateur :', error);
      }
    }




const getLieuxById = async () => {
        try {
            const response = await axios.get(`http://localhost:8002/lieux/${props.idMembre}`);
            
            console.log('loggg:', response.data);
            lieu.value = response.data
        }
        catch (error) {
        console.error('Erreur lors de la récupération des membres:', error);
      }
        // TODO faire appelel axios
    } 


   const getLieux = async () => {
  try {
    console.log("kkfrf")
    const response = await axios.get(`http://localhost:8002/lieux`);
    const lieux = response.data;
    
   lieux.forEach(lieu => {
      listLieuxx.value.push(lieu);
   });
    //store.listMembres.push(listMembress)
    store.state.listLieux = listLieuxx.value;

    console.log('Noms des aliments par type:', listLieuxx.value);
    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
    
    onMounted(async () => {
    
    await getLieuxById();
       nom.value = lieu.value.nom
         adresse.value = lieu.value.adresse
        capacite.value = lieu.value.capacite

      });
   

    return {
      nom,
      lieu,
      adresse,
      capacite,
      listLieuxx,
      closeModalUpdateUser,
      updatedLieux,
      
    };
      
  }
}
</script>

<style scoped>
/* Styles pour centrer la modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Fond sombre semi-transparent */
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Styles pour le contenu de la modal */
.modal-content {
  width: 400px; /* Largeur de la modal */
  background-color: white; /* Couleur de fond blanche */
  padding: 20px;
  color: black;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Styles pour le bouton de fermeture */
.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

/* Styles pour le corps de la modal */
.modal-body {
  /* Styles pour le contenu du corps de la modal */
}

/* Styles pour les éléments du formulaire */
.form-group {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
  display: block;
}

input {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
