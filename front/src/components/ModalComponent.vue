<template>
  <!-- Modal Overlay -->
  <div class="modal-overlay">
    <!-- Modal Content -->
    <div class="modal-content">
      <!-- Close Button -->
      <span @click="closeModalAddUser()" class="close-btn">&times;</span>
      <!-- Modal Body -->
      <div class="modal-body">
        <!-- Content Here -->
        <h2>Ajouter un utilisateur</h2>
        <form @submit.prevent="createUser">
          <div class="form-group">
            <label for="picture">Photo de profil:</label>
            <FileUpload mode="basic" name="demo[]" url="/api/upload" accept="image/*" :maxFileSize="1000000" @upload="onUpload" />
          </div>
          <div class="form-group">
            <label for="name">Email:</label>
            <input type="text" id="email" required v-model="email">
          </div>
          <div class="form-group">
            <label for="age">Nom:</label>
            <input type="text" id="nom" required v-model="nom">
          </div>
          <div class="form-group">
            <label for="city">Prenom:</label>
            <input type="text" id="prenom" required v-model="prenom">
          </div>
          <div class="form-group">
            <label for="email">Role:</label>
            <input type="text" id="role" required v-model="role">
          </div>
          <div class="form-group">
            <label for="phone">Adresse:</label>
            <input type="text" id="adresse" required v-model="adresse">
          </div>
          <div class="form-group">
            <label for="post">Photo:</label>
            <input type="text" id="photo" required v-model="photo">
          </div>
          <button type="submit">Ajouter</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import FileUpload from 'primevue/fileupload';
import axios from 'axios';
export default {
  name: 'ModalComponent', 
  
  components: { FileUpload },
props: {
    
    type:{
      type: Number,
      required: true
    }
  }, 
  setup() {
    const store = useStore();
    const email = ref('');
    const nom = ref('');
    const prenom = ref('');
    const role = ref('');
    const adresse = ref('');
    const photo = ref('');
            const listMembress = ref([]);


    const closeModalAddUser = () => {
      store.commit('toogleOpenModalUserAddFalse');
    }

    const createUser = async()  => {
      const newUser = {
        email: email.value,
        nom: nom.value,
        prenom: prenom.value,
        role: role.value,
        adresse: adresse.value,
        photo: photo.value,
      };
       
      
      // Ajoutez ici la logique pour envoyer les données de l'utilisateur au backend (par exemple, avec axios)
      console.log('Nouvel utilisateur :', newUser);
    try {
        // Effectuer la requête POST avec Axios
        const response = await axios.post('http://localhost:8080/membres', newUser);
        console.log('Réponse du serveur :', response.data);
        
        // Réinitialiser les champs du formulaire après la soumission
        email.value = '';
        nom.value = '';
        prenom.value = '';
        role.value = '';
        adresse.value = '';
        photo.value = '';
        
       
      } catch (error) {
        console.error('Erreur lors de la création de l\'utilisateur :', error);
      }


      
      // Réinitialiser les champs du formulaire après la soumission
      email.value = '';
      nom.value = '';
      prenom.value = '';
      role.value = '';
      adresse.value = '';
      photo.value = '';
 
      // Fermer la modal
            await getMembre();

      await getPhotos();
      console.log("toototoototototootooto")
      closeModalAddUser();
    }


    const getMembre = async () => {
  try {
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
}
    const getPhotos = async () => {
         try {
            
            const response = await axios.get(`https://api.unsplash.com/search/photos?query=user&client_id=nhr9wJnIHxnWeDBWu7E6HT8qMZhFcGZaGDwi7H6S-YA`);
            const photos = response.data.results; 

            const smallImageUrls = photos.map(item => item.urls.small);
            console.log("icici -->   " + store.state.listMembres.length);
            store.state.photoMembres = smallImageUrls.slice(0, store.state.listMembres.length);
            console.log("je passe làJJJJJJ")
     
            } catch (error) {
                console.error('Erreur lors de la récupération des photos via api:', error);
            }
        }
    

    onMounted(() => {
      console.log('frfr');
    });

    return {
      email,
      nom,
      prenom,
      role,
      adresse,
      photo,
      closeModalAddUser,
      createUser
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
