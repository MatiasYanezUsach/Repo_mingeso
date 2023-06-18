import { Container, Row, Form, FormGroup, Col, Card, Button } from 'react-bootstrap';
import { useState } from 'react';
import JustificativoService from '../services/JustificativoService';

const Justificativos = () => {

    const initialState = {
        rut_empleado: '',
        fecha_cubridora: ''
    };

    const [justificados, setJustificados] = useState(initialState);

    const changeRutHandler = event => {
        setJustificados({ ...justificados, rut_empleado: event.target.value });
        console.log(justificados.rut_empleado);
    };

    const changeFechaHandler = event => {
        setJustificados({ ...justificados, fecha_cubridora: event.target.value });
        console.log(justificados.fecha_cubridora);
    };

    const setJustificativo = e => {
        e.preventDefault();
        try {
            let justificativo = { rut_empleado: justificados.rut_empleado, fecha_cubridora: justificados.fecha_cubridora };
            console.log(justificados.rut_empleado)
            console.log(justificados.fecha_cubridora)
            console.log("justificativo => " + JSON.stringify(justificativo));
            JustificativoService.IngresarJustificativo(justificativo);
            alert("Se ha subido la justificacion");
        } catch (error) {
            console.log(error.message)
        }
    }


    return (
        <Container style={{ marginTop: '70px' }}>
            <Card className='text-center'>
                <Row>
                    <Col><h1>Subir un justificativo</h1><br></br> </Col>
                </Row>
                <Row>
                    <Form className='text-center'>
                        <FormGroup controlId="rut" value={justificados.rut_empleado} onChange={changeRutHandler}>
                            <label><h5>Rut del empleado: </h5></label>
                            <br></br>
                            <h5><input placeholder='12.345.678-9' name="rut" clasname="form-control"></input></h5>
                        </FormGroup>
                        <FormGroup controlId="fecha" value={justificados.fecha_cubridora} onChange={changeFechaHandler}>
                            <label><h5>Fecha a cubrir: </h5></label>
                            <br></br>
                            <h5><input placeholder='YYYY-MM-DD' name="fecha" clasname="form-control"></input></h5>
                        </FormGroup>
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <Button onClick={setJustificativo} className="btn btn-success btn-default">Guardar</Button>
                            <Button className="btn btn-danger btn-default">Cancelar</Button>
                        </div>
                    </Form>
                </Row>
            </Card>
        </Container>
    )
}

export default Justificativos;