using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("subcategories")]
[Index("Categoryid", Name = "FK5cs3pf1w7kktveo6g9o25ita6")]
public partial class Subcategory
{
    [Key]
    [Column("subcategoryid")]
    public int Subcategoryid { get; set; }

    [Column("subcategoryimage")]
    [StringLength(255)]
    public string? Subcategoryimage { get; set; }

    [Column("subcategoryname")]
    [StringLength(255)]
    public string Subcategoryname { get; set; } = null!;

    [Column("categoryid")]
    public int Categoryid { get; set; }

    [ForeignKey("Categoryid")]
    [InverseProperty("Subcategories")]
    public virtual Category Category { get; set; } = null!;

    [InverseProperty("Subcategory")]
    public virtual ICollection<Product> Products { get; set; } = new List<Product>();
}
