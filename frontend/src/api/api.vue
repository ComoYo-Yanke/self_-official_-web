<script setup>
import axios from 'axios';

const response = await axios.get('http://127.0.0.1:8080/depts');

const data = response.data; // 后端js数据

console.log(data);

export function showData() {

    console.log(data);
}
</script>
