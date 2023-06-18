import './App.css';
import { Routes, Route } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import Layout from './Layout';
import Index from './pages/Index';
import Justificativos from './pages/Justificativos'
import Solicitudes from './pages/Solicitudes'
import ReporteSueldo from './pages/ReporteSueldo'
import Marcas from './pages/Marcas'

function App() {
  return (
    <Layout>
      <Container>
        <Routes>
          <Route path='/' element={<Index />} exact/>
          <Route path='/Justificativos' element={<Justificativos/>} exact />
          <Route path='/Solicitudes' element={<Solicitudes/>} exact />
          <Route path='/ReporteSueldo' element={<ReporteSueldo/>} exact />
          <Route path='/Marcas' element={<Marcas/>} exact />
        </Routes>
      </Container>
    </Layout>
  );
}

export default App;
