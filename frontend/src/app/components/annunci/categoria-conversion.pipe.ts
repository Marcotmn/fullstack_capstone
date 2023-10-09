// categoria-conversion.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'categoriaConversion',
})
export class CategoriaConversionPipe implements PipeTransform {
  transform(categoria: string): string {
    // Sostituisci gli underscore con spazi vuoti
    return categoria.replace(/_/g, ' ');
  }
}
