<template>
  <div class="card" :style="cardStyle" @click="handleCardClick">
    <h3 class="title">Test card {{index}}</h3>
    <p class="description">Le lorem ipsum est, en imprimerie, une suite de mots sans signification utilisée à titre provisoire</p>
  </div>
</template>

<script>
import { onMounted } from 'vue';
export default {
  name: 'CardComponent',
  props: {
    index: {
      type: Number,
      required: true
    }
  },
  computed: {
    cardStyle() {
      return {
       //backgroundImage: `url(${require(`../images/pic-1.png`)})`
        backgroundImage: `url(${require(`../images/pic-2.png`)})`


      };
    }
  },
  setup() {
    
    
    const handleCardClick = () => {
      console.log("dcede ")
      
    };
    

    onMounted( () => {
        console.log('edfefde');
    });
  
    return {
      handleCardClick,
    };
  }
}
</script>

<style scoped>
.card {
  width: 200px;
  height: 230px;
  display: inline-block;
  border-radius: 10px;
  padding: 15px 25px;
  box-sizing: border-box;
  cursor: pointer;
  margin: 10px 15px;
  background-position: center;
  transition: transform 0.5s;
}

.card:hover {
  transform: translateY(-10px);
}



.description {
  color: white; 
}
</style>
