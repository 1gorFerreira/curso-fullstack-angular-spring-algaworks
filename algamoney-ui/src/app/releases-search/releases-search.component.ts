import { Component } from '@angular/core';

@Component({
  selector: 'app-releases-search',
  templateUrl: './releases-search.component.html',
  styleUrl: './releases-search.component.css'
})
export class ReleasesSearchComponent {
  releases = [
    { type: 'DESPESA', description: 'Compra de pão', dueDate: new Date(2017, 5, 30),
      dataPagamento: null, value: 4.55, person: 'Padaria do José' },
    { type: 'RECEITA', description: 'Venda de software', dueDate: new Date(2017, 6, 10),
      paymentDate: new Date(2017, 6, 9), value: 80000, person: 'Atacado Brasil' },
    { type: 'DESPESA', description: 'Impostos', dueDate: new Date(2017, 7, 20),
      paymentDate: null, value: 14312, person: 'Ministério da Fazenda' },
    { type: 'DESPESA', description: 'Mensalidade de escola', dueDate: new Date(2017, 6, 5),
      paymentDate: new Date(2017, 5, 30), value: 800, person: 'Escola Abelha Rainha' },
    { type: 'RECEITA', description: 'Venda de carro', dueDate: new Date(2017, 8, 18),
      paymentDate: null, value: 55000, person: 'Sebastião Souza' },
    { type: 'DESPESA', description: 'Aluguel', dueDate: new Date(2017, 7, 10),
      paymentDate: new Date(2017, 7, 9), value: 1750, person: 'Casa Nova Imóveis' },
    { type: 'DESPESA', description: 'Mensalidade musculação', dueDate: new Date(2017, 7, 13),
      paymentDate: null, value: 180, person: 'Academia Top' }
  ];
}
