import { Container, Row, Form, FormGroup, Col, Card, Button } from 'react-bootstrap';
import { useState } from 'react';
import SolicitudService from '../services/SolicitudService';


const Solicitudes = () => {

    const initialState = {
        rut_empleado: '',
        fecha_cubridora: ''
    };

    const [solicitudes, setSolicitudes] = useState(initialState);

    const changeRutHandler = event => {
        setSolicitudes({ ...solicitudes, rut_empleado: event.target.value });
        console.log(solicitudes.rut_empleado);
    };

    const changeFechaHandler = event => {
        setSolicitudes({ ...solicitudes, fecha_cubridora: event.target.value });
        console.log(solicitudes.fecha_cubridora);
    };

    const setSolicitud = e => {
        e.preventDefault();
        try {
            let solicitud = { rut_empleado: solicitudes.rut_empleado, fecha_cubridora: solicitudes.fecha_cubridora };
            console.log(solicitudes.rut_empleado)
            console.log(solicitudes.fecha_cubridora)
            console.log("solicitud => " + JSON.stringify(solicitud));
            SolicitudService.IngresarSolicitud(solicitud);
            alert("Se ha subido la solicitud");
        } catch (error) {
            console.log(error.message)
        }
    }

return (
    <Container style={{ marginTop: '70px' }}>
        <Card className='text-center'>
            <Row>
                <Col><h1>Subir una solicitud</h1><br></br> </Col>
            </Row>
            <Row>
                <Form className='text-center'>
                    <FormGroup controlId="rut" value={solicitudes.rut_empleado} onChange={changeRutHandler}>
                        <label><h5>Rut del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='12.345.678-9' name="rut" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="fecha" value={solicitudes.fecha_cubridora} onChange={changeFechaHandler}>
                        <label><h5>Fecha a cubrir: </h5></label>
                        <br></br>
                        <h5><input placeholder='YYYY-MM-DD' name="fecha" clasname="form-control"></input></h5>
                    </FormGroup>
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <Button onClick={setSolicitud} className="btn btn-success btn-default">Guardar</Button>
                        <Button className="btn btn-danger btn-default">Cancelar</Button>
                    </div>
                </Form>
            </Row>
        </Card>
    </Container>
    )
};

export default Solicitudes;