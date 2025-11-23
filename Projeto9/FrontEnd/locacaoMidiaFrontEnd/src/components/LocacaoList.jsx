import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function LocacaoList() {
    const [locacoes, setLocacoes] = useState([]);
    const [itemLocacao, setLocacao] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
}

export default LocacaoList;