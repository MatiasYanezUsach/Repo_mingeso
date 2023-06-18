import { useState } from 'react';
import { Container, Row, Form, Col, Button, Card} from 'react-bootstrap';
import axios from 'axios';

const Marcas = () => {

    const [selectedFile, setSelectedFile] = useState(null);

    const handleUpload = (e) => {
        e.preventDefault();

        let url = 'http://localhost:8080/marcas/importar';
        let form_data = new FormData();
        form_data.append("file", selectedFile);

        axios.post(url, form_data)
            .then((response) => {
                alert(response.data);
            })
            .catch((err) => {
                alert(err.response.data);
            })
    }

    const alerta = () =>{
        alert("Se han subido las marcas de reloj!")
    }

    return (
        <Container style={{ marginTop: '70px' }}>
            <Card className='text-center'>
                <Row>
                    <Col><h1>Marcas de reloj</h1><br></br> </Col>
                </Row>
                <Row className="mt-5">
                    <Row className='text-center'>
                        <Form onSubmit={handleUpload}>
                            <Form.Group controlId="formFile" className="mb-3">
                                <Form.Control
                                    type="file"
                                    size="lg"
                                    required
                                    onChange={(e) => setSelectedFile(e.target.files[0])}
                                />
                            </Form.Group>
                            <Button type="submit" onClick={alerta} className="btn btn-success btn-lg">Subir</Button>
                        </Form>
                    </Row>
                </Row>
            </Card>
        </Container>
    )
};

export default Marcas;