import { Component } from '@angular/core';

@Component({
  selector: 'app-releases-search',
  templateUrl: './releases-search.component.html',
  styleUrl: './releases-search.component.css'
})
export class ReleasesSearchComponent {
  releases = [
    { type: 'DESPESA', description: 'Compra de pão', dueDate: '30/06/2017',
      dataPagamento: null, value: 4.55, person: 'Padaria do José' },
    { type: 'RECEITA', description: 'Venda de software', dueDate: '10/06/2017',
      paymentDate: '09/06/2017', value: 80000, person: 'Atacado Brasil' },
    { type: 'DESPESA', description: 'Impostos', dueDate: '20/07/2017',
      paymentDate: null, value: 14312, person: 'Ministério da Fazenda' },
    { type: 'DESPESA', description: 'Mensalidade de escola', dueDate: '05/06/2017',
      paymentDate: '30/05/2017', value: 800, person: 'Escola Abelha Rainha' },
    { type: 'RECEITA', description: 'Venda de carro', dueDate: '18/08/2017',
      paymentDate: null, value: 55000, person: 'Sebastião Souza' },
    { type: 'DESPESA', description: 'Aluguel', dueDate: '10/07/2017',
      paymentDate: '09/07/2017', value: 1750, person: 'Casa Nova Imóveis' },
    { type: 'DESPESA', description: 'Mensalidade musculação', dueDate: '13/07/2017',
      paymentDate: null, value: 180, person: 'Academia Top' }
  ];
}
