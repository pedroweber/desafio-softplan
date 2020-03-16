import { Component, Input, OnInit, Injectable } from '@angular/core';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PessoaServiceService } from '../../../api/pessoa-service.service';
import { Validacoes } from 'src/app/validacoes';
import { formatDate } from '@angular/common';


@Injectable({
  providedIn: "root"
})

@Component({
  selector: 'app-info-pessoa',
  templateUrl: './info-pessoa.component.html',
  styleUrls: ['./info-pessoa.component.scss']
})
export class InfoPessoaComponent implements OnInit {

  @Input() public pessoaSelected;

  public pessoaForm: FormGroup;

  pessoa: any;
  constructor(
    public activeModal: NgbActiveModal, private service: PessoaServiceService
  ) { }

  ngOnInit() {
    this.pessoa = {};
    this.pessoa = this.pessoaSelected;
    
    this.pessoaForm = new FormGroup({
      'nome': new FormControl(this.pessoa.nome, [Validators.required]),
      'sexo': new FormControl(this.pessoa.sexo),
      'email': new FormControl(this.pessoa.email, [Validators.email]),
      'dataNascimento': new FormControl(formatDate(this.pessoa.dataNascimento, 'yyyy-MM-dd', 'en')),
      'naturalidade': new FormControl(this.pessoa.naturalidade),
      'nacionalidade': new FormControl(this.pessoa.nacionalidade),
      'cpf': new FormControl(this.pessoa.cpf, Validators.compose([Validators.required, Validacoes.ValidaCpf]))
    });
  }

  editar() {
    this.service.editar(this.pessoaForm.value).subscribe(resposta => {
      this.pessoaForm.reset();
    });
    this.activeModal.close();
  }

  deletar() {
    this.service.deletar(this.pessoaForm.value).subscribe(resposta => {
      this.activeModal.close();
    });;

  }

  close() {
    this.activeModal.close();
  }
}