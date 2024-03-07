<template>
  <div class="leaflet-map" ref="mapContainer"></div>
</template>

<script>
import { ref, onMounted } from 'vue';
import L from 'leaflet';

export default {
  setup() {
    const mapContainer = ref(null); // Référence réactive pour stocker le conteneur de la carte
    let map = null;

    onMounted(() => {
      // Récupérer le conteneur de la carte à partir de la référence réactive
      const container = mapContainer.value;

      // Vérifier si le conteneur est défini avant de créer la carte
      if (container) {
        // Créer une carte Leaflet et l'attacher au conteneur
        map = L.map(container).setView([51.505, -0.09], 13);

        // Ajouter une couche OpenStreetMap à la carte
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors'
        }).addTo(map);
      }
    });

    return {
      mapContainer
    };
  }
}
</script>

<style scoped>
.leaflet-map {
  height: 100px; /* Ajustez la hauteur de la carte selon vos besoins */
  width: 50px;
}
</style>
