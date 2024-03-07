<template>
  <div>
    <!--<td>{{ itemsMembres.id }}</td>
    <td><img :src="itemPhoto" alt="" width="50" height="50"></td>
    <td>{{ itemsMembres.nom }}</td>
    <td>{{ itemsMembres.prenom }}</td>
    <td>{{ itemsMembres.adresse }}</td>
    <td>{{ itemsMembres.email }}</td>                            
    <td> 
      <button class="btn btn-primary"><i class="bi bi-pencil-square"></i></button>
      <button @click="openModalConfirmDelete(membre.id)" class="btn btn-danger"><i class="bi bi-trash"></i></button>
    </td> 
    -->
     <td>eede</td>
    <td>ded</td>
    <td>dede</td>
    <td>dede</td>
    <td>ded</td>
    <td>deded@dede</td>                            
    <td> 
      <button class="btn btn-primary"><i class="bi bi-pencil-square"></i></button>
      <button class="btn btn-danger"><i class="bi bi-trash"></i></button>
    </td> 
  </div>
</template>

<script>
import { onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'LineMember',
  props: {
     itemsMembres: {
      type: Array,
      required: true
    },
     itemPhoto: {
      type: String,
      required: true
    },
  },
  setup(props) {
    const store = useStore();

     const openModalConfirmDelete =  () => {
            store.commit('toggleopenModalConfirmDeleteTrue');
        };
    onMounted(()=>{
      console.log('hiih -> ' + props.itemsMembres);
      console.log('hiih22 -> ' + props.itemPhoto);

    })
    return {
      openModalConfirmDelete,
    };

  }
};
</script>

<style scoped>
td{
    color: #fff;
}
tr td{
    vertical-align: middle;
}
td button{
    margin: 5px;
}
td button i{
    font-size: 20px;
}
</style>
