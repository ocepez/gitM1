import { createRouter, createWebHistory } from 'vue-router';
import AcceuilPage from '../pages/AcceuilPage.vue'; // importez vos composant
import MembrePage from '@/pages/MembrePage.vue'
import ConnectePage from '@/pages/ConnectePage.vue'
import EvenementV2Page from '@/pages/EvenementV2Page.vue'
import AdminPage from '@/pages/AdminPage.vue'
import TestPage from '@/pages/TestPage.vue';
import LieuxPage from '@/pages/LieuxPage.vue';

//import ConnectPage from '@/pages/ConnectPage.vue';

//import ConnectPage from '../pages/ConnectPage.vue'; // importez vos composants
//import MenuPage from '../pages/MenuPage.vue'; // importez vos composants
//import MenuAdminPage from'../pages/MenuAdminPage';
const routes = [
  {
    path: '/',
    component: AcceuilPage
  },
  /*{
    path: '/envenement',
    component: EnvenementPage
  }, 
  {
    path: '/connect',
    component: ConnectPage
  },*/
  {
    path: '/test',
    component: TestPage
  },
  {
    path: '/membre',
    component: MembrePage
  },
  {
    path: '/lieux',
    component: LieuxPage
  },
  {
    path: '/connecte',
    component: ConnectePage
  },
 
  {
    path: '/evenement',
    component: EvenementV2Page
  },
  {
    path: '/membre',
    component: MembrePage
  },
  {
    path: '/admin',
    component: AdminPage
  },
  /*{
    path: '/menu',
    component: MenuPage
  },
  {
    path: '/connect',
    component: ConnectPage
  },
  {
    path: '/menuAdmin',
    component: MenuAdminPage
  },*/
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;