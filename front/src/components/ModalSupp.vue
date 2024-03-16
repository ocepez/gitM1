<template>
  <!-- Modal Overlay -->
  <div class="modal-overlay">
    <!-- Modal Content -->
    <div class="modal-content">
      <!-- Close Button -->
      <span @click="closeModal()" class="close-btn">&times;</span>
      <!-- Modal Body -->
      <div class="modal-body">
        <!-- Contenu conditionnel de la modal -->
        <h2 v-if="type === 1">Confirmer l'annulation du membre</h2>
        <h2 v-else-if="type === 2">Confirmer l'annulation de l'événement</h2>
        <h2 v-else-if="type === 3">Confirmer l'annulation du lieux</h2>

        <p v-if="type === 1">Êtes-vous sûr de vouloir annuler ce membre ?</p>
        <p v-else-if="type === 2">Êtes-vous sûr de vouloir annuler cet événement ?</p>
        <p v-else-if="type === 3">Êtes-vous sûr de vouloir annuler ce lieux ?</p>

        <button v-if="type === 1" @click="confirmCancel">Confirmer</button>
        <button v-else-if="type === 2" @click="confirmCancelEvent">Confirmer</button>
                <button v-else-if="type === 3" @click="confirmCancelLieux">Confirmer</button>

      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import axios from 'axios';
import { ref } from 'vue';

export default {
  name: 'ModalSupp',
  props: {
    idMembre: {
      type: Number,
      required: true
    },
    type:{
      type: Number,
      required: true
    }
  },  
  setup(props) {
    const store = useStore();
        const listMembress = ref([]);
        const listEventss = ref([])

    const closeModal = () => {
      store.commit('toggleopenModalConfirmDeleteFalse');
    };
    const confirmCancelEvent = async () =>{
        try {
            const response = await axios.delete(`http://localhost:8000/evenements/${props.idMembre}`);
            console.log('suprression membre:', response);
            store.commit('toggleopenModalConfirmDeleteFalse');
            //getMembre()
        }
        catch (error) {
        console.error('Erreur lors de la récupération des membres:', error);
      }
    }
    const confirmCancelLieux = async () => {
      try {
            const response = await axios.delete(`http://localhost:8002/lieux/${props.idMembre}`);
            console.log('suprression membre:', response);
            store.commit('toggleopenModalConfirmDeleteFalse');
            await getLieux()
        }
        catch (error) {
        console.error('Erreur lors de la récupération des lieux:', error);
      }
      console.log("jfnrfjkrjkfrik")
    }

    const confirmCancel = async () => {
        try {
            const response = await axios.delete(`http://localhost:8080/membres/${props.idMembre}`);
            console.log('suprression membre:', response);
            store.commit('toggleopenModalConfirmDeleteFalse');
            await getMembre();
        }
        catch (error) {
        console.error('Erreur lors de la récupération des membres:', error);
      }
        // TODO faire appelel axios
    } 
    const getEvents = async () => {
  try {
    const response = await axios.get(`http://localhost:8000/evenements`);
    const envenements = response.data;
    
   envenements.forEach(event => {
      listEventss.value.push(event);
   });
    //store.listMembres.push(listMembress)
    store.state.listEvents = listEventss.value;

    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
const getLieux = async () => {
  try {
    console.log("kkfrf")
    const response = await axios.get(`http://localhost:8002/lieux`);
    const membres = response.data;
    
   membres.forEach(membre => {
      listMembress.value.push(membre);
   });
    //store.listMembres.push(listMembress)
    store.state.listLieux = listMembress.value;

    console.log('Noms des aliments par type:', listMembress.value);
    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
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

    return {
      closeModal,
      confirmCancel,
      confirmCancelEvent,
      getEvents,
      confirmCancelLieux,
    };
  }
};
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
