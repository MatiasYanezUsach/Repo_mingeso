import axios from 'axios';

class JustificativoService {
    IngresarJustificativo(justificativo) {
        return axios.post('http://localhost:8080/justificativo/subirJustificativo', justificativo)
    }
}

export default new JustificativoService();