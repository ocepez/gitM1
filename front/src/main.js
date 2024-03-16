import { createApp } from 'vue';
import router from './router';
import App from './App.vue';
import PrimeVue from 'primevue/config';
import 'primevue/resources/themes/saga-green/theme.css'; // Choisir le thème souhaité
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
//import 'primevue/resources/primevue.min.css'; // Styles de base
//import 'primeicons/primeicons.css'
import store from './store';

const app = createApp(App);
//app.use(router);
app.use(store)
app.use(router).mount('#app');
app.use(PrimeVue);

