<template>
  <div class="envenementPage">
    <HeaderComponentV2></HeaderComponentV2>
    <div class="filterEvent">
      <div class="filterInputs">
        <input class="input-motcle" type="text" placeholder="Recherche par mot clé">
        <select class="input-select">
          <option value="">Filtrer par</option>
          <option value="today">Evenement les plus recents</option>
          <option value="this_week">Evenement les plus peuplé</option>
          <option value="this_month">Evenement les mieux noté</option>
          <!-- Ajoutez d'autres options selon vos besoins -->
        </select>
        <select class="input-select">
          <option value="asc">Croissant</option>
          <option value="desc">Décroissant</option>
        </select>
      </div>
    </div>
    <div class="containerCard">
                        <CardComponentMenu v-for="index in 12" :key="index" :index="index"/>

        <!-- Contenu de votre carte -->
      </div>
    </div>
</template>

<script>
import HeaderComponentV2 from'@/components/HeaderComponentV2.vue'
import CardComponentMenu from '@/components/CardComponentMenu.vue'
export default {
  name: 'EnvenementPage',
  components: {
    HeaderComponentV2,
    CardComponentMenu,
  },
  data() {
    return {
      events: [
        // Vos données d'événement ici
      ]
    }
  }
}
</script>
<style scoped>
.envenementPage {
}

.filterEvent {
    margin-top: 30px;
  margin-bottom: 20px;
  color: white; /* Couleur du texte */
}

.filterEvent h1 {
  font-size: 1.5em;
}

.filterInputs {
  display: flex;
  align-items: center;
}

.filterInputs input[type="text"], .filterInputs select {
  margin-right: 10px;
  background-color: #333; /* Couleur de fond sombre */
  color: white; /* Couleur du texte */
  border: 1px solid #555; /* Bordure */
  border-radius: 5px; /* Coins arrondis */
  padding: 8px 10px; /* Espacement intérieur */
}

.filterInputs input[type="text"]::placeholder {
  color: #ccc; /* Couleur du texte de l'attribut placeholder */
}

.containerCard {
  display: flex;
  flex-wrap: wrap;
}
.input-motcle{
    margin-left: 12px;
    margin-right: 150px !important;
}
.input-select{
    margin-right: 150px !important;

}
.card {
  flex: 1 0 calc(25% - 20px); /* 4 cartes par ligne avec une marge */
  margin: 10px;
  border: 1px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
}
</style>
